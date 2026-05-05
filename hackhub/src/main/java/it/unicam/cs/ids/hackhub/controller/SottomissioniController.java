package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.InviaSottomissioneRequest;
import it.unicam.cs.ids.hackhub.dto.request.ValutaSottomissioneRequest;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Valutazione;
import it.unicam.cs.ids.hackhub.service.SottomissioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sottomissione")
public class SottomissioniController {
    @Autowired
    private SottomissioniService sottomissioniService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Sottomissione>> getAllSottomissioni(
            @RequestParam long staffID,
            @RequestParam long hackathonID
    ){
        List<Sottomissione> sottomissioni = sottomissioniService.getAllSottomissioni(staffID, hackathonID);
        return ResponseEntity.status(HttpStatus.OK).body(sottomissioni);
    }

    @GetMapping(value = "/{sottomissioneID}")
    public ResponseEntity<Sottomissione> getSottomissioneById(
            @PathVariable("sottomissioneID") long sottomissioneID,
            @RequestParam long staffID
    ){
        Sottomissione sottomissione = sottomissioniService.getSottomissioneById(sottomissioneID, staffID);
        return ResponseEntity.status(HttpStatus.OK).body(sottomissione);
    }

    @PutMapping(value = "/{sottomissioneID}/send")
    public ResponseEntity<Boolean> inviaSottomissione(
            @PathVariable("sottomissioneID") long sottomissioneID,
            @RequestBody InviaSottomissioneRequest inviaSottomissioneRequest
    ){
        sottomissioniService.inviaSottomissione(sottomissioneID, inviaSottomissioneRequest.membroID());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/{sottomissioneID}/evaluate")
    public ResponseEntity<Valutazione> valutaSottomissione(
            @PathVariable("sottomissioneID") long sottomissioneID,
            @RequestBody ValutaSottomissioneRequest valutaSottomissioneRequest
    ){
        Valutazione valutazione =
                sottomissioniService.valutaSottomissione(sottomissioneID,
                        valutaSottomissioneRequest.giudiceID(),
                        valutaSottomissioneRequest.punteggio(),
                        valutaSottomissioneRequest.giudizioScritto());
        return ResponseEntity.status(HttpStatus.OK).body(valutazione);
    }
}
