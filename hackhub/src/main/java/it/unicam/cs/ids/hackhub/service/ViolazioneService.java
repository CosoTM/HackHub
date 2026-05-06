package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ConflictException;
import it.unicam.cs.ids.hackhub.exception.api.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import it.unicam.cs.ids.hackhub.repository.ViolazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViolazioneService {
    @Autowired
    private ViolazioneRepository violazioneRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HackathonRepository hackathonRepository;
    @Autowired
    private TeamRepository teamRepository;


    public List<Violazione> getAllViolazioni(long mentoreID, long hackathonID) {
        Hackathon hackathon = findHackathonOrThrow(hackathonID);
        Utente mentore = findUserOrThrow(mentoreID);

        if(mentore.hasTipoUtente(UtenteType.MENTORE)) throw new ForbiddenOperationException();
        if(!hackathon.hasMentore(mentore)) throw new ForbiddenOperationException("Non sei mentore dello Staff dell'Hackathon");

        return violazioneRepository.findByHackathon(hackathon);
    }

    public Violazione getViolazioneById(long sottomissioneID, long mentoreID) {
        Violazione violazione = findViolazioneOrThrow(sottomissioneID);
        Utente mentore = findUserOrThrow(mentoreID);

        if(mentore.hasTipoUtente(UtenteType.MENTORE)) throw new ForbiddenOperationException();

        return violazione;
    }

    public Violazione segnalaTeam(long mentoreID, long teamID, long hackathonID, String motivoSegnalazione) {
        Hackathon hackathon = findHackathonOrThrow(hackathonID);
        Utente mentore = findUserOrThrow(mentoreID);

        if(mentore.hasTipoUtente(UtenteType.MENTORE)) throw new ForbiddenOperationException();
        if(!hackathon.hasMentore(mentore)) throw new ForbiddenOperationException("Non sei mentore dello Staff dell'Hackathon");

        Team team = findTeamOrThrow(teamID);
        if(!hackathon.hasTeamIscritto(team)) throw new ConflictException("Il Team non è iscritto all'Hackathon");

        Violazione violazione = new Violazione();
        violazione.setMentore(mentore);
        violazione.setTeam(team);
        violazione.setHackathon(hackathon);
        violazione.setMotivoSegnalazione(motivoSegnalazione);

        violazioneRepository.save(violazione);

        return violazione;
    }

    private Violazione findViolazioneOrThrow(long id){
        return violazioneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Violazione non trovata con id: "+ id));
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
