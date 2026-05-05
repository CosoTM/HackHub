package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackathonService {
    @Autowired
    private HackathonRepository hackathonRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    public void penalizzaTeam(long hackathonID, long staffID, long teamID,
                              int penalizzazione) {
        Team team =
                teamRepository.findById(teamID).orElseThrow(() -> new ResourceNotFoundException(
                        "Team non trovato con id: "+ teamID));
        Utente staff =
                userRepository.findById(staffID).orElseThrow(() -> new ResourceNotFoundException(
                        "Utente non trovato " + "con id: " + staffID));

        if (!staff.hasTipoUtente(UtenteType.ORGANIZZATORE)) throw new ForbiddenOperationException();

        Hackathon hackathon = hackathonRepository.findById(hackathonID).orElseThrow(() ->
                new ResourceNotFoundException("Hackathon non trovato con id: "+ hackathonID));

        if(!hackathon.getOrganizzatore().equals(staff)) throw new ForbiddenOperationException();
        if(!hackathon.getTeamIscritti().contains(team)) throw new ForbiddenOperationException("Il Team non è iscritto all'hackathon");

        team.penalizza(penalizzazione);

        teamRepository.save(team);
    }

    public List<Hackathon> getAllHackathons() {
        List<Hackathon> hs = hackathonRepository.findAll();
        return hs;
    }

    public Hackathon getHackathonById(long hackathonID) {
        return findHackathonOrThrow(hackathonID);
    }

    private Hackathon findHackathonOrThrow(long id){
        return hackathonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Hackathon non trovato con id: "+ id));
    }

    public List<Hackathon> getOngoingHackathons() {
        List<Hackathon> hs = hackathonRepository.findByStato(StatoHackathon.ISCRIZIONE);
        return hs;
    }
}
