package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.dto.request.RegistraUtenteRequest;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Utente> registraUtente(
            @Valid @RequestBody RegistraUtenteRequest registraUtenteRequest){
        Utente saved = userService.registraUtente(registraUtenteRequest.nome(),
                registraUtenteRequest.email(), registraUtenteRequest.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Utente> getUserById(@PathVariable("id") long id){
        Utente utente = userService.getUtenteById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(utente);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Utente> getUserByName(@PathVariable("name") String name){
        Utente utente = userService.getUtenteByName(name);
        return  ResponseEntity.status(HttpStatus.OK).body(utente);
    }

}
