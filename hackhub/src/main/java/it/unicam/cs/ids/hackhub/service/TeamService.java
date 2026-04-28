package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    public Team createTeam(long userId, String nomeTeam) {
        Utente utente =
                userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utente non trovato " +
                "con id: " + userId));
        Team team = new Team();
        team.setNomeTeam(nomeTeam);
        team.setCapoTeam(utente);
        team.addMembroTeam(utente);
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public void addMember(long userId, long teamId) {
        Utente utente =
                userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utente non trovato " +
                        "con id: " + userId));
        Team team =
                teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException(
                        "Team non trovato " +
                        "con id: " + teamId));

        team.addMembroTeam(utente);
        teamRepository.save(team);
    }
}
