package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private UserRepository userRepository;
}
