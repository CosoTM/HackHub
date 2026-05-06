package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ConflictException;
import it.unicam.cs.ids.hackhub.exception.api.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.SottomissioneRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SottomissioniService {
    @Autowired
    private SottomissioneRepository sottomissioneRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HackathonRepository hackathonRepository;

    public List<Sottomissione> getAllSottomissioni(long staffID, long hackathonID) {
        Hackathon hackathon = findHackathonOrThrow(hackathonID);
        Utente staff = findUserOrThrow(staffID);

        checkIfStaff(staff);

        if(!hackathon.isStaff(staff)) throw new ForbiddenOperationException("Non sei parte dello Staff dell'Hackathon");

        return sottomissioneRepository.findByHackathon(hackathon);
    }
    public Sottomissione getSottomissioneById(long sottomissioneID, long staffID) {
        Sottomissione sottomissione = findSottomissioneOrThrow(sottomissioneID);
        Utente staff = findUserOrThrow(staffID);

        checkIfStaff(staff);

        Hackathon hackathon = sottomissione.getHackathon();
        if(!hackathon.isStaff(staff)) throw new ForbiddenOperationException("Non sei parte dello Staff dell'Hackathon");

        return sottomissione;
    }

    public void inviaSottomissione(long sottomissioneID, long membroID) {
        Sottomissione sottomissione = findSottomissioneOrThrow(sottomissioneID);
        Utente membro = findUserOrThrow(membroID);

        Team team = sottomissione.getTeam();
        if(!team.hasMembroTeam(membro)) throw new ForbiddenOperationException("Non fai parte del team");

        Hackathon hackathon = sottomissione.getHackathon();
        if(!hackathon.hasTeamIscritto(team)) throw new ConflictException("Il Team non è iscritto all'Hackathon");

        Date now = new Date();
        if(now.after(hackathon.getScadenzaSottomissioni())) throw new ConflictException("Non è più possibile inviare la sottomissione");

        sottomissione.setInviata(true);
        sottomissione.setDataConsegna(new Date());
        sottomissioneRepository.save(sottomissione);
    }

    public Valutazione valutaSottomissione(long sottomissioneID, long giudiceID, int punteggio, String giudizioScritto) {
        Sottomissione sottomissione = findSottomissioneOrThrow(sottomissioneID);
        Utente giudice = findUserOrThrow(giudiceID);

        if (!giudice.hasTipoUtente(UtenteType.GIUDICE)) throw new ForbiddenOperationException();

        Hackathon hackathon = sottomissione.getHackathon();
        if(!hackathon.getGiudice().equals(giudice)) throw new ForbiddenOperationException("Non sei giudice dell'Hackathon");
        if(!sottomissione.isInviata()) throw new ConflictException("La sottomissione non è ancora giudicabile");

        Valutazione valutazione = new Valutazione();
        valutazione.setPunteggio(punteggio);
        valutazione.setGiudizioScritto(giudizioScritto);

        sottomissione.setValutazione(valutazione);
        sottomissioneRepository.save(sottomissione);

        return valutazione;
    }

    private void checkIfStaff(Utente utente){
        if(!utente.hasTipoUtente(UtenteType.GIUDICE) &&
           !utente.hasTipoUtente(UtenteType.MENTORE) &&
           !utente.hasTipoUtente(UtenteType.ORGANIZZATORE)) {
            throw new ForbiddenOperationException("Non sei parte dello Staff della piattaforma");
        }
    }

    private Sottomissione findSottomissioneOrThrow(long id){
        return sottomissioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Sottomissione non trovata con id: "+ id));
    }

    private Hackathon findHackathonOrThrow(long id){
        return hackathonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Hackathon non trovato con id: "+ id));
    }

    private Utente findUserOrThrow(long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "User non trovato con id: "+ id));
    }



}
