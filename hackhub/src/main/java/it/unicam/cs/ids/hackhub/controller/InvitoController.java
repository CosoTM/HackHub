package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.model.Invito;
import it.unicam.cs.ids.hackhub.service.InvitoService;
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

    @GetMapping(value = "/getInvitoById/{id}")
    public ResponseEntity<Invito> getInvitoById(@PathVariable("id") long id){
        Invito saved = invitoService.getInvitoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    @GetMapping(value = "/getAllInvitiForUser/{userId}")
    public ResponseEntity<List<Invito>> getAllInvitiForUser(@PathVariable(
            "userId") long userId){
        List<Invito> saved = invitoService.getAllInvitiForUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    @PostMapping(value = "/accettaInvito/{userId}/{invitoId}")
    public ResponseEntity<Boolean> accettaInvito(@PathVariable("userId") long userId,
                                                 @PathVariable("invitoId") long invitoId) {
        invitoService.accettaInvito(userId, invitoId);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
