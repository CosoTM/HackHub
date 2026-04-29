package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HackathonService {
    @Autowired
    private HackathonRepository hackathonRepository;


}
