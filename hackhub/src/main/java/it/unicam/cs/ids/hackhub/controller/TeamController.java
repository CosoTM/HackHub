package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamService teamService;


    @RequestMapping(value = "/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("ciao sto funzionando", HttpStatus.OK);
    }
}
