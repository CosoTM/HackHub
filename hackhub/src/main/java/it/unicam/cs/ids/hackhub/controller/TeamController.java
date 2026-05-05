package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.AbbandonaTeamRequest;
import it.unicam.cs.ids.hackhub.dto.request.CreaTeamRequest;
import it.unicam.cs.ids.hackhub.dto.request.EspelliMembroRequest;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.service.TeamService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<Team>> getAllTeams(@RequestParam long hackathonId){
        List<Team> teams = teamService.getAllTeams(hackathonId);
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @GetMapping(value = "/{teamID}")
    public ResponseEntity<Team> getTeamById(@PathVariable("teamID") long teamID){
        Team team = teamService.getTeamByID(teamID);
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Team> createTeam(@Valid @RequestBody CreaTeamRequest creaTeamRequest){
        Team created = teamService.createTeam(creaTeamRequest.userID(), creaTeamRequest.nomeTeam());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/{teamID}/expel")
    public ResponseEntity<Void> espelliMembro(@PathVariable("teamID") long teamID,
                                              @Valid @RequestBody EspelliMembroRequest espelliMembroRequest){
        teamService.espelliMembro(teamID, espelliMembroRequest.capoID(),
                espelliMembroRequest.userEspulsoID());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/{teamID}/leave")
    public ResponseEntity<Boolean> abbandonaTeam(@PathVariable("teamID") long teamID, @Valid @RequestBody AbbandonaTeamRequest abbandonaTeamRequest){
        teamService.abbandonaTeam(teamID, abbandonaTeamRequest.membroID());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
