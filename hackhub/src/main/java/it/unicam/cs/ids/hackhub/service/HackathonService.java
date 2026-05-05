package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.dto.request.CreaHackathonRequest;
import it.unicam.cs.ids.hackhub.exception.api.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.model.hackathonBuilder.ConcreteHackathonBuilder;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.SottomissioneRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HackathonService {
    @Autowired
    private HackathonRepository hackathonRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SottomissioneRepository sottomissioneRepository;

    public void penalizzaTeam(long hackathonID, long staffID, long teamID, int penalizzazione) {
        Team team = findTeamOrThrow(teamID);
        Utente staff = findUserOrThrow(staffID);

        if (!staff.hasTipoUtente(UtenteType.ORGANIZZATORE)) throw new ForbiddenOperationException();

        Hackathon hackathon = findHackathonOrThrow(hackathonID);

        if(!hackathon.getOrganizzatore().equals(staff)) throw new ForbiddenOperationException();
        if(!hackathon.getTeamIscritti().contains(team)) throw new ForbiddenOperationException("Il Team non è iscritto all'hackathon");

        team.penalizza(penalizzazione);

        teamRepository.save(team);
    }


    public List<Hackathon> getOngoingHackathons() {
        return hackathonRepository.findByStatoHackathon(StatoHackathon.ISCRIZIONE);
    }

    public Hackathon createHackathon(CreaHackathonRequest creaHackathonRequest){
        Utente organizzatore =
                findUserOrThrow(creaHackathonRequest.organizzatoreID());

        if (!organizzatore.hasTipoUtente(UtenteType.ORGANIZZATORE)) throw new ForbiddenOperationException();

        Utente giudice = findUserOrThrow(creaHackathonRequest.giudiceID());
        if (!giudice.hasTipoUtente(UtenteType.GIUDICE)) throw new ForbiddenOperationException("L'utente non è un giudice");

        List<Utente> mentori = new ArrayList<>();
        for (long mentoreID: creaHackathonRequest.mentoriID()) {
            Utente mentore = findUserOrThrow(mentoreID);
            if (!mentore.hasTipoUtente(UtenteType.MENTORE)) throw new ForbiddenOperationException("L'utente non è un mentore");
            mentori.add(mentore);
        }

        ConcreteHackathonBuilder hackathonBuilder = new ConcreteHackathonBuilder();
        hackathonBuilder.buildNome(creaHackathonRequest.nome());
        hackathonBuilder.buildRegolamento(creaHackathonRequest.regolamento());
        hackathonBuilder.buildScadenzaIscrizioni(creaHackathonRequest.scadenzaIscrizioni());
        hackathonBuilder.buildDataInizio(creaHackathonRequest.dataInizio());
        hackathonBuilder.buildDataFine(creaHackathonRequest.dataFine());
        hackathonBuilder.buildLuogo(creaHackathonRequest.luogo());
        hackathonBuilder.buildPremio(creaHackathonRequest.premio());
        hackathonBuilder.buildDimensioneMassimo(creaHackathonRequest.dimensioneMassimoTeam());
        hackathonBuilder.buildScadenzaSottomissioni(creaHackathonRequest.scadenzaSottomissioni());
        hackathonBuilder.buildGiudice(giudice);
        hackathonBuilder.buildMentori(mentori);
        hackathonBuilder.buildOrganizzatore(organizzatore);
        Hackathon h = hackathonBuilder.getResult();

        hackathonRepository.save(h);
        return h;
    }

    public List<Hackathon> getAllHackathons() {
        return hackathonRepository.findAll();
    }

    public Hackathon getHackathonById(long hackathonID) {
        return findHackathonOrThrow(hackathonID);
    }

    public boolean iscriviTeam(long hackathonID, long teamID, long userID) {
        Hackathon hackathon = findHackathonOrThrow(hackathonID);
        Team team = findTeamOrThrow(teamID);

        if(hackathon.hasTeamIscritto(team)) throw new ForbiddenOperationException("Il team è gia iscritto all'Hackathon");
        if(team.getMembriTeam().size() > hackathon.getDimensioneMassimaTeam()) throw new ForbiddenOperationException("Il numero di membri del team supera il massimo numero permesso");

        for (Utente membro: team.getMembriTeam()){
            if(hackathon.hasUtenteEscluso(membro)) throw new ForbiddenOperationException(membro.getEmail() + "è stato escluso dall'Hackathon. Il Team non può iscriversi all'Hackathon");
            if(hackathon.isStaff(membro)) throw new ForbiddenOperationException(membro.getEmail() + "è parte dello staff dell'Hackathon. Il Team non può iscriversi all'Hackathon");
        }

        Utente capo = findUserOrThrow(userID);
        if(!team.getCapoTeam().equals(capo)) throw new ForbiddenOperationException("Non sei capo del team");

        Sottomissione sottomissione = new Sottomissione();
        sottomissione.setTeam(team);

        sottomissioneRepository.save(sottomissione);

        hackathon.addTeam(team);
        team.addHackathonIscritto(hackathon);

        teamRepository.save(team);
        hackathonRepository.save(hackathon);

        return true;
    }

    public boolean bannaTeam(long hackathonID, long staffID, long teamID) {
        Hackathon hackathon = findHackathonOrThrow(hackathonID);
        Team team = findTeamOrThrow(teamID);

        if(!hackathon.hasTeamIscritto(team)) throw new ForbiddenOperationException("Il team non è iscritto all'Hackathon");
        if(!hackathon.getStatoHackathon().equals(StatoHackathon.IN_CORSO)) throw new ForbiddenOperationException("L'hackathon non è in corso");

        Utente organizzatore = findUserOrThrow(staffID);
        if (!hackathon.getOrganizzatore().equals(organizzatore)) throw new ForbiddenOperationException();

        hackathon.removeTeam(team);
        team.removeHackathonIscritto(hackathon);
        for (Utente membro : team.getMembriTeam()) {
            hackathon.addUtenteEscluso(membro);
        }

        teamRepository.save(team);
        hackathonRepository.save(hackathon);

        return true;
    }

    public boolean proclamaVincitore(long hackathonID, long staffID, long teamID) {
        Hackathon hackathon = findHackathonOrThrow(hackathonID);
        Team team = findTeamOrThrow(teamID);

        if(!hackathon.hasTeamIscritto(team)) throw new ForbiddenOperationException("Il team non è iscritto all'Hackathon");
        // TODO: nuovo stato di fine valutazione??
        if(hackathon.getStatoHackathon() != StatoHackathon.VALUTAZIONE) throw new ForbiddenOperationException("L'Hackathon non può essere terminato nello stato corrente");

        Utente organizzatore = findUserOrThrow(staffID);
        if (!hackathon.getOrganizzatore().equals(organizzatore)) throw new ForbiddenOperationException();

        hackathon.setStatoHackathon(StatoHackathon.CONCLUSO);
        // TODO: salvare team vincitore??
        // sistema di pagamento

        hackathonRepository.save(hackathon);
        return true;
    }

    private Hackathon findHackathonOrThrow(long id){
        return hackathonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Hackathon non trovato con id: "+ id));
    }

    private Utente findUserOrThrow(long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "User non trovato con id: "+ id));
    }

    private Team findTeamOrThrow(long id){
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Team non trovato con id: "+ id));
    }
}
