package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.model.UtenteType;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HackathonRepository hackathonRepository;

    public Team createTeam(long userId, String nomeTeam) {
        Utente utente =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                        "Utente non trovato " +
                "con id: " + userId));
        Team team = new Team();
        team.setNomeTeam(nomeTeam);
        team.addMembroTeam(utente);
        team.setCapoTeam(utente);

        return teamRepository.save(team);
    }

    public List<Team> getAllTeams(){
        // TODO: filtra per hackathon
        return teamRepository.findAll();
    }

    public void aggiungiMembro(Utente utente, Team team){
        team.addMembroTeam(utente);
        teamRepository.save(team);
    }

    public void espelliMembro(long teamID, long capoID, long userEspulsoID){
        // TODO: metodi privati per non ripetere cerca o lancia???
        Team team =
                teamRepository.findById(teamID).orElseThrow(() -> new ResourceNotFoundException(
                        "Team non trovato con id: "+ teamID));
        Utente capo = userRepository.findById(capoID).orElseThrow(() -> new ResourceNotFoundException(
                "Utente non trovato " + "con id: " + capoID));

        if (!team.hasMembroTeam(capo)) throw new ForbiddenOperationException(
                "Non fai parte del team");
        if (team.getCapoTeam().equals(capo)) throw new ForbiddenOperationException(
                "Non sei capo del team");

        Utente espulso =
                userRepository.findById(userEspulsoID).orElseThrow(() -> new ResourceNotFoundException(
                "Utente non trovato " + "con id: " + userEspulsoID));

        if (capo.equals(espulso)) throw new ForbiddenOperationException("Non " +
                "puoi espellerti da solo");
        if (!team.hasMembroTeam(espulso)) throw new ForbiddenOperationException("L'utente non fa parte del team");

        team.removeMembroTeam(espulso);
        teamRepository.save(team);
    }

    public void abbandonaTeam(long teamID, long membroID){
        Team team =
                teamRepository.findById(teamID).orElseThrow(() -> new ResourceNotFoundException(
                        "Team non trovato con id: "+ teamID));
        Utente membro =
                userRepository.findById(membroID).orElseThrow(() -> new ResourceNotFoundException(
                "Utente non trovato " + "con id: " + membroID));

        if(!team.hasMembroTeam(membro)) throw new ForbiddenOperationException(
                "Non fai parte del team");

        if(team.getMembriTeam().size() == 1){
            teamRepository.delete(team);
            return;
        }

        team.removeMembroTeam(membro);
        teamRepository.save(team);
    }
}
