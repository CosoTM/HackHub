package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.*;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hackathon")
public class HackathonController {
    @Autowired
    private HackathonService hackathonService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Hackathon>> getAllHackathons(){
        List<Hackathon> hs = hackathonService.getAllHackathons();
        return ResponseEntity.status(HttpStatus.OK).body(hs);
    }

    @GetMapping(value = "/{hackathonID}")
    public ResponseEntity<Hackathon> getHackathonById(@PathVariable(
            "hackathonID") long hackathonID){
        Hackathon h = hackathonService.getHackathonById(hackathonID);
        return ResponseEntity.status(HttpStatus.OK).body(h);
    }

    @GetMapping(value = "/ongoing")
    public ResponseEntity<List<Hackathon>> getOngoingHackatons(){
        List<Hackathon> hs = hackathonService.getOngoingHackathons();
        return ResponseEntity.status(HttpStatus.OK).body(hs);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Hackathon> createHackathon(@RequestBody CreaHackathonRequest creaHackathonRequest){
        return null;
    }

    @PutMapping(value = "/{hackathonID}/iscriviTeam")
    public ResponseEntity<List<Hackathon>> iscriviTeam(
            @PathVariable("hackathonID") long hackathonID,
            @RequestBody IscriviTeamRequest iscriviTeamRequest){
        return null;
    }


    @PutMapping(value = "/{hackathonID}/penalizza")
    public ResponseEntity<Boolean> penalizzaTeam(@PathVariable("hackathonID") long teamID,
                                                 @RequestBody PenalizzaTeamRequest penalizzaTeamRequest){
        hackathonService.penalizzaTeam(teamID, penalizzaTeamRequest.staffID(),
                penalizzaTeamRequest.teamID(),
                penalizzaTeamRequest.penalizzazione());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/{hackathonID}/banna")
    public ResponseEntity<Boolean> bannaTeam(@PathVariable("hackathonID") long teamID,
                                         @RequestBody BannaTeamRequest penalizzaTeamRequest){
       return null;
    }

    @PutMapping(value = "/{hackathonID}/vincitore")
    public ResponseEntity<Boolean> proclamaVincitore(@PathVariable("hackathonID") long teamID,
                                             @RequestBody ProclamaVincitoreRequest proclamaVincitoreRequest){
        return null;
    }

}
