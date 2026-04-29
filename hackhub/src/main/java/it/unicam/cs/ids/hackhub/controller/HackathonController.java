package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hackathon")
public class HackathonController {
    @Autowired
    private HackathonService hackathonService;


}
