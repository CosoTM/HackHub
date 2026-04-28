package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
public class TeamController {

    @Autowired
    private TeamService teamService;


    @PostMapping(value = "/createTeam/{userId}")
    public ResponseEntity<Team> createTeam(@PathVariable("userId") long userId, @RequestBody String nomeTeam){
        Team saved = teamService.createTeam(userId, nomeTeam);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping(value = "/addMember/{userId}/{teamId}")
    public ResponseEntity<Object> addMember(@PathVariable("userId") long userId
            , @PathVariable("teamId") long teamId){
        teamService.addMember(userId, teamId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/getAllTeams/{hackathonId}")
    public ResponseEntity<List<Team>> getAllTeams(@PathVariable("hackathonId") long hackathonId){
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.status(HttpStatus.OK).body(teams);

    }
}
