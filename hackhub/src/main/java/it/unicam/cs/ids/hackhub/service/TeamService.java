package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
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

    private Team findTeamOrThrow(long id){
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Team non trovato con id: "+ id));
    }
    private Utente findUserOrThrow(long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Utente non trovato " + "con id: " + id));
    }

    public Team createTeam(long userId, String nomeTeam) {
        Utente utente = findUserOrThrow(userId);

        if (utente.getTeam() != null) throw new ForbiddenOperationException(
                "Fai gia parte di un team. Esci da questo team prima di crearne un'altro");

        Team team = new Team();
        team.setNomeTeam(nomeTeam);
        team.addMembroTeam(utente);
        team.setCapoTeam(utente);

        return teamRepository.save(team);
    }

    public List<Team> getAllTeams(long hackathonID){
        // TODO: se non ci sono team iscritti all'hackathon? Dovrebbe
        //  restituire qualcos'altro
        return teamRepository.findByHackathonIscritti_ID(hackathonID);
    }

    public void espelliMembro(long teamID, long capoID, long userEspulsoID){
        Team team = findTeamOrThrow(teamID);
        Utente capo = findUserOrThrow(capoID);

        if (!team.hasMembroTeam(capo)) throw new ForbiddenOperationException("Non fai parte del team");
        if (team.getCapoTeam().equals(capo)) throw new ForbiddenOperationException("Non sei capo del team");

        Utente espulso = findUserOrThrow(userEspulsoID);

        if (capo.equals(espulso)) throw new ForbiddenOperationException("Non puoi espellerti da solo");
        if (!team.hasMembroTeam(espulso)) throw new ForbiddenOperationException("L'utente non fa parte del team");

        team.removeMembroTeam(espulso);
        teamRepository.save(team);
    }

    public void abbandonaTeam(long teamID, long membroID){
        Team team = findTeamOrThrow(teamID);
        Utente membro = findUserOrThrow(membroID);

        if(!team.hasMembroTeam(membro)) throw new ForbiddenOperationException("Non fai parte del team");

        if(team.getMembriTeam().size() == 1){
            teamRepository.delete(team);
            return;
        }

        team.removeMembroTeam(membro);
        teamRepository.save(team);
    }

    public void aggiungiMembro(Utente utente, Team team){
        team.addMembroTeam(utente);
        teamRepository.save(team);
    }
}
