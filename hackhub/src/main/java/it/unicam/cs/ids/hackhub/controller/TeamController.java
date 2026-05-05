package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.AbbandonaTeamRequest;
import it.unicam.cs.ids.hackhub.dto.request.CreaTeamRequest;
import it.unicam.cs.ids.hackhub.dto.request.EspelliMembroRequest;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.service.TeamService;
import jakarta.websocket.server.PathParam;
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

    @GetMapping(value = "/")
    public ResponseEntity<List<Team>> getAllTeams(@PathParam("hackathonId") long hackathonId){
        List<Team> teams = teamService.getAllTeams(hackathonId);
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Team> createTeam(@RequestBody CreaTeamRequest creaTeamRequest){
        Team created = teamService.createTeam(creaTeamRequest.userID(), creaTeamRequest.nomeTeam());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/{teamID}/espelli")
    public ResponseEntity<Void> espelliMembro(@PathVariable("teamID") long teamID,
                                           @RequestBody EspelliMembroRequest espelliMembroRequest){
        teamService.espelliMembro(teamID, espelliMembroRequest.capoID(),
                espelliMembroRequest.userEspulsoID());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/{teamID}/abbandona")
    public ResponseEntity<Boolean> abbandonaTeam(@PathVariable("teamID") long teamID, @RequestBody AbbandonaTeamRequest abbandonaTeamRequest){
        teamService.abbandonaTeam(teamID, abbandonaTeamRequest.membroID());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
