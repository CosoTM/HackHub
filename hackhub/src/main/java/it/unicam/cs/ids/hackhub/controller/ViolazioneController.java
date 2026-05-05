package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.SegnalaTeamRequest;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Violazione;
import it.unicam.cs.ids.hackhub.service.ViolazioneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/violazioni")
public class ViolazioneController {
    private ViolazioneService violazioneService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Violazione>> getAllViolazioni(
            @RequestParam long staffID,
            @RequestParam long hackathonID
    ){
        List<Violazione> violazione =
                violazioneService.getAllViolazioni(staffID, hackathonID);
        return ResponseEntity.status(HttpStatus.OK).body(violazione);
    }

    @GetMapping(value = "/{violazioneID}")
    public ResponseEntity<Violazione> getSottomissioneById(
            @PathVariable("violazioneID") long violazioneID,
            @RequestParam long staffID
    ){
        Violazione violazione =
                violazioneService.getViolazioneById(violazioneID,
                staffID);
        return ResponseEntity.status(HttpStatus.OK).body(violazione);
    }

    @PostMapping(value = "/report")
    public ResponseEntity<Violazione> segnalaTeam(
            @RequestBody SegnalaTeamRequest segnalaTeamRequest
    ){
        Violazione violazione = violazioneService.segnalaTeam(
                segnalaTeamRequest.mentoreID(),
                segnalaTeamRequest.teamID(),
                segnalaTeamRequest.hackathonID(),
                segnalaTeamRequest.motivoSegnalazione()
        );

        return ResponseEntity.status(HttpStatus.OK).body(violazione);
    }
}
