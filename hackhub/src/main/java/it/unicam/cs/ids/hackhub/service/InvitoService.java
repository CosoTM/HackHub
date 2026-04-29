package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.Invito;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.InvitoRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvitoService {

    @Autowired
    private InvitoRepository invitoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamService teamService;

    public Invito getInvitoById(Long id) {
        return invitoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invito non trovato con id "+ id));

    }

    public List<Invito> getAllInvitiForUser(Long userId){
        List<Invito> allInviti = invitoRepository.findAll();

        List<Invito> invitiPerUser = new ArrayList<>();

        // TODO: Filtra inviti

        return invitiPerUser;
    }

    public boolean accettaInvito(long userId, long invitoId) {
        Utente utente =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                        "Utente non trovato " +
                                "con id: " + userId));
        Invito invito =
                invitoRepository.findById(invitoId).orElseThrow(() -> new ResourceNotFoundException(
                        "Invito non trovato " +
                                "con id: " + invitoId));

        Team teamInvitante = invito.getTeamInvitante();
        teamService.addMember(utente, teamInvitante);

        return true;
    }
}
