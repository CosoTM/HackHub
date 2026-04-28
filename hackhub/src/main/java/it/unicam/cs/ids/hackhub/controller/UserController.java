package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/createUser")
    public ResponseEntity<Utente> createUtente(@RequestBody Utente utente){
        Utente saved = userService.createUtente(utente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping(value = "/getUserById/{id}")
    public ResponseEntity<Utente> getUserById(@PathVariable("id") long id){
        Utente utente = userService.getUtenteById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(utente);
    }

    @GetMapping(value = "/getUserByName/{name}")
    public ResponseEntity<Utente> getUserByName(@PathVariable("name") String name){
        Utente utente = userService.getUtenteByName(name);
        return  ResponseEntity.status(HttpStatus.OK).body(utente);
    }
}
