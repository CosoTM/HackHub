package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/createUser", method = RequestMethod.POST)
    public ResponseEntity<Utente> createUtente(@RequestBody Utente utente){
        System.out.println(utente.getTipoUtente());
        return new ResponseEntity<>(utente, HttpStatus.CREATED);
    }
}
