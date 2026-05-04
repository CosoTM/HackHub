package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.PenalizzaTeamRequest;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hackathon")
public class HackathonController {
    @Autowired
    private HackathonService hackathonService;

    @PutMapping(value = "/{hackathonID}/penalizza")
    public ResponseEntity<Boolean> penalizzaTeam(@PathVariable("hackathonID") long teamID,
                                                 @RequestBody PenalizzaTeamRequest penalizzaTeamRequest){
        hackathonService.penalizzaTeam(teamID, penalizzaTeamRequest.staffID(),
                penalizzaTeamRequest.teamID(),
                penalizzaTeamRequest.penalizzazione());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
