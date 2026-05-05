package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.AccettaInvitoRequest;
import it.unicam.cs.ids.hackhub.dto.request.InvitaUtenteRequest;
import it.unicam.cs.ids.hackhub.model.Invito;
import it.unicam.cs.ids.hackhub.service.InvitoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invito")
public class InvitoController {
    @Autowired
    private InvitoService invitoService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Invito>> getAllInvitiForUser(
            @RequestParam long userId){
        List<Invito> saved = invitoService.getAllInvitiForUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Invito> getInvitoById(@PathVariable("id") long id){
        Invito saved = invitoService.getInvitoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }


    @PutMapping(value = "/{invitoID}/accetta")
    public ResponseEntity<Boolean> accettaInvito(@PathVariable("invitoID") long invitoId,
                                                 @Valid @RequestBody AccettaInvitoRequest accettaInvitoRequest) {
        invitoService.accettaInvito(invitoId, accettaInvitoRequest.userID());
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping(value = "/invite")
    public ResponseEntity<Invito> invitaUtente(@Valid @RequestBody InvitaUtenteRequest invitaUtenteRequest) {
        Invito invito = invitoService.invitaUtente(invitaUtenteRequest.teamID(),
                invitaUtenteRequest.capoID(), invitaUtenteRequest.userInvitatoID());
        return ResponseEntity.status(HttpStatus.OK).body(invito);
    }
}
