package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ConflictException;
import it.unicam.cs.ids.hackhub.exception.api.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.RichiestaSupprtoRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RichiestaSupportoService {

    @Autowired
    private RichiestaSupprtoRepository richiestaSupprtoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HackathonRepository hackathonRepository;
    @Autowired
    private TeamRepository teamRepository;

    public List<RichiestaSupporto> getAllRichiesteSupporto(long mentoreID, long hackathonID) {
        Hackathon hackathon = findHackathonOrThrow(hackathonID);
        Utente mentore = findUserOrThrow(mentoreID);

        if(!mentore.hasTipoUtente(UtenteType.MENTORE)) throw new ForbiddenOperationException();
        if (!hackathon.hasMentore(mentore)) throw new ForbiddenOperationException("Non sei un mentore dell'Hackathon");

        return richiestaSupprtoRepository.findByHackathon(hackathon);
    }

    public RispostaSupporto rispondiRichiestaSupporto(long richiestaID, long mentoreID, String risposta) {
        RichiestaSupporto richiestaSupporto = fineRichiestaOrThrow(richiestaID);
        Utente mentore = findUserOrThrow(mentoreID);

        if(!mentore.hasTipoUtente(UtenteType.MENTORE)) throw new ForbiddenOperationException();

        Hackathon hackathon = richiestaSupporto.getHackathon();
        if (!hackathon.hasMentore(mentore)) throw new ForbiddenOperationException("Non un mentore dell'Hackathon");

        RispostaSupporto rispostaSupporto = new RispostaSupporto();
        rispostaSupporto.setMentore(mentore);
        rispostaSupporto.setRisposta(risposta);

        richiestaSupporto.addRisposta(rispostaSupporto);
        richiestaSupprtoRepository.save(richiestaSupporto);

        return rispostaSupporto;
    }

    public RichiestaSupporto inviaRichiestaSupporto(long teamID, long hackathonID,
                                                    String titolo, String corpo) {
        Team team = findTeamOrThrow(teamID);
        Hackathon hackathon = findHackathonOrThrow(hackathonID);

        if(!hackathon.hasTeamIscritto(team)) throw new ConflictException("Il Team non è iscritto all'Hackathon");

        RichiestaSupporto richiestaSupporto = new RichiestaSupporto();
        richiestaSupporto.setHackathon(hackathon);
        richiestaSupporto.setTeam(team);
        richiestaSupporto.setTitolo(titolo);
        richiestaSupporto.setCorpo(corpo);

        richiestaSupprtoRepository.save(richiestaSupporto);
        return richiestaSupporto;
    }

    private RichiestaSupporto fineRichiestaOrThrow(long id){
        return richiestaSupprtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Richiesta di supporto non trovata con id: "+ id));
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
