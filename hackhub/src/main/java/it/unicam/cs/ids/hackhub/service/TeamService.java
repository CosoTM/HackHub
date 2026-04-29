package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;
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
        return teamRepository.findAll();
    }

    public void addMember(long userId, long teamId) {
        Utente utente =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                        "Utente non trovato " +
                        "con id: " + userId));
        Team team =
                teamRepository.findById(teamId).orElseThrow(() -> new ResourceNotFoundException(
                        "Team non trovato " +
                        "con id: " + teamId));

        addMember(utente, team);
    }

    public void addMember(Utente utente, Team team){
        team.addMembroTeam(utente);
        teamRepository.save(team);
    }
}
