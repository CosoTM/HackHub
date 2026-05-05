package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.*;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.hackathonBuilder.ConcreteHackathonBuilder;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.service.HackathonService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Hackathon> createHackathon(@Valid @RequestBody CreaHackathonRequest creaHackathonRequest){
        Hackathon h = hackathonService.createHackathon(creaHackathonRequest);
        return ResponseEntity.status(HttpStatus.OK).body(h);
    }

    @PutMapping(value = "/{hackathonID}/subscribe")
    public ResponseEntity<Boolean> iscriviTeam(
            @PathVariable("hackathonID") long hackathonID,
            @RequestBody IscriviTeamRequest iscriviTeamRequest){

        hackathonService.iscriviTeam(hackathonID, iscriviTeamRequest.teamID(),
                iscriviTeamRequest.capoID());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping(value = "/{hackathonID}/penalize")
    public ResponseEntity<Boolean> penalizzaTeam(@PathVariable("hackathonID") long hackathonID,
                                                 @Valid @RequestBody PenalizzaTeamRequest penalizzaTeamRequest){
        hackathonService.penalizzaTeam(hackathonID, penalizzaTeamRequest.staffID(),
                penalizzaTeamRequest.teamID(),
                penalizzaTeamRequest.penalizzazione());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/{hackathonID}/ban")
    public ResponseEntity<Boolean> bannaTeam(@PathVariable("hackathonID") long hackathonID,
                                             @Valid @RequestBody BannaTeamRequest bannaTeamRequest){
       hackathonService.bannaTeam(hackathonID, bannaTeamRequest.staffID(),
               bannaTeamRequest.teamID());
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/{hackathonID}/winner")
    public ResponseEntity<Boolean> proclamaVincitore(@PathVariable("hackathonID") long hackathonID,
                                                     @Valid @RequestBody ProclamaVincitoreRequest proclamaVincitoreRequest){
        hackathonService.proclamaVincitore(hackathonID,
                proclamaVincitoreRequest.teamID(),
                proclamaVincitoreRequest.organizzatoreID());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
