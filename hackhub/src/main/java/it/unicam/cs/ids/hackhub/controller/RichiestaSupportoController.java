package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.RispondiRichiestaRequest;
import it.unicam.cs.ids.hackhub.model.RichiestaSupporto;
import it.unicam.cs.ids.hackhub.model.RispostaSupporto;
import it.unicam.cs.ids.hackhub.service.RichiestaSupportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supporto")
public class RichiestaSupportoController {
    @Autowired
    private RichiestaSupportoService richiestaSupportoService;

    @GetMapping(value = "/")
    public ResponseEntity<List<RichiestaSupporto>> getAllRichiesteSupporto(
            @RequestParam long mentoreID,
            @RequestParam long hackathonID
    ){
        List<RichiestaSupporto> richiesteSupporto = richiestaSupportoService.getAllRichiesteSupporto(mentoreID, hackathonID);
        return ResponseEntity.status(HttpStatus.OK).body(richiesteSupporto);
    }

    @PutMapping(value = "/{richiestaID}/reply")
    public ResponseEntity<RispostaSupporto> rispondiRichiestaSupporto(
            @PathVariable("richiestaID") long richiestaID,
            @RequestBody RispondiRichiestaRequest rispondiRichiestaRequest
    ){
        RispostaSupporto risposta =
                richiestaSupportoService.rispondiRichiestaSupporto(
                        richiestaID,
                        rispondiRichiestaRequest.mentoreID(),
                        rispondiRichiestaRequest.risposta()
                );
        return ResponseEntity.status(HttpStatus.OK).body(risposta);
    }

    @PutMapping(value = "/call")
    public ResponseEntity<Void> organizzaCall(){
        return null;
    }
}
