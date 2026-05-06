package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ConflictException;
import it.unicam.cs.ids.hackhub.exception.api.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.repository.InvitoRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitoService {

    @Autowired
    private InvitoRepository invitoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamService teamService;

    public Invito getInvitoById(Long id) {
        return findInvitoOrThrow(id);

    }

    public List<Invito> getAllInvitiForUser(Long userId){
        Utente utente = findUserOrThrow(userId);
        return invitoRepository.findByUtenteInvitato(utente);
    }

    public boolean accettaInvito(long invitoId, long userId) {
        Invito invito = findInvitoOrThrow(invitoId);
        Utente utente = findUserOrThrow(userId);

        if(utente.getTeam() != null) throw new ConflictException("Fai gia parte di un team");
        if(!invito.getUtenteInvitato().equals(utente)) throw new ForbiddenOperationException("Non sei il destinatario dell'invito");

        Team teamInvitante = invito.getTeamInvitante();

        for (Hackathon hackathon: teamInvitante.getHackathonIscritti()) {
            if(hackathon.getStatoHackathon() == StatoHackathon.IN_CORSO)
                throw new ConflictException("Il Team sta correntemente partecipando ad uno o più hackathon in corso. Attendi che finisca per entrare nel team");
        }

        teamService.aggiungiMembro(utente, teamInvitante);
        invitoRepository.delete(invito);

        return true;
    }

    public Invito invitaUtente(long teamID, long capoID, long userID) {
        Team team = findTeamOrThrow(teamID);
        Utente capo = findUserOrThrow(capoID);
        Utente utente = findUserOrThrow(userID);

        if(!team.hasMembroTeam(capo)) throw new ForbiddenOperationException("Non fai parte del team");
        if(!team.getCapoTeam().equals(capo)) throw new ForbiddenOperationException("Non sei il capo del team");
        if(team.hasMembroTeam(utente)) throw new ConflictException(utente.getEmail()+" fa gia parte del team");

        for (Hackathon hackathon: team.getHackathonIscritti()) {
            if(hackathon.getStatoHackathon() == StatoHackathon.IN_CORSO)
                throw new ConflictException("Il Team sta " +
                        "correntemente partecipando all'Hackathon: "+ hackathon.getNome() + ". Attendi che finisca per invitare nuovi utenti nel team");

            if(hackathon.getDimensioneMassimaTeam() < team.getMembriTeam().size()+1)
                throw new ConflictException("L'aggiunta del utente porterebbe la dimensione del team sopra a quella permessa dall'Hackathon "+ hackathon.getNome() + "a cui il team sta partecipando.");
        }

        Invito invito = new Invito();
        invito.setUtenteInvitato(utente);
        invito.setTeamInvitante(team);

        invitoRepository.save(invito);

        return invito;
    }

    private Invito findInvitoOrThrow(long id){
        return invitoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Invito non trovato " + "con id: " + id));
    }

    private Utente findUserOrThrow(long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Utente non trovato " + "con id: " + id));
    }

    private Team findTeamOrThrow(long id){
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Team non trovato con id: "+ id));
    }
}
