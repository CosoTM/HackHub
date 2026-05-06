package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ForbiddenOperationException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.RichiestaSupprtoRepository;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RichiestaSupportoService {

    @Autowired
    private RichiestaSupprtoRepository richiestaSupprtoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HackathonRepository hackathonRepository;

    public List<RichiestaSupporto> getAllRichiesteSupporto(long mentoreID, long hackathonID) {
        Hackathon hackathon = findHackathonOrThrow(hackathonID);
        Utente mentore = findUserOrThrow(mentoreID);

        if(mentore.hasTipoUtente(UtenteType.MENTORE)) throw new ForbiddenOperationException();
        if (!hackathon.hasMentore(mentore)) throw new ForbiddenOperationException("Non sei un mentore dell'Hackathon");

        return richiestaSupprtoRepository.findByHackathon(hackathon);
    }

    public RispostaSupporto rispondiRichiestaSupporto(long richiestaID, long mentoreID, String risposta) {
        RichiestaSupporto richiestaSupporto = fineRichiestaOrThrow(richiestaID);
        Utente mentore = findUserOrThrow(mentoreID);

        if(mentore.hasTipoUtente(UtenteType.MENTORE)) throw new ForbiddenOperationException();

        Hackathon hackathon = richiestaSupporto.getHackathon();
        if (hackathon.hasMentore(mentore)) throw new ForbiddenOperationException("Non un mentore dell'Hackathon");

        RispostaSupporto rispostaSupporto = new RispostaSupporto();
        rispostaSupporto.setMentore(mentore);
        rispostaSupporto.setRisposta(risposta);

        richiestaSupporto.addRisposta(rispostaSupporto);
        richiestaSupprtoRepository.save(richiestaSupporto);

        return rispostaSupporto;
    }

    private RichiestaSupporto fineRichiestaOrThrow(long id){
        return richiestaSupprtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Richiesta di supporto non trovata con id: "+ id));
    }

    private Hackathon findHackathonOrThrow(long id){
        return hackathonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Hackathon non trovato con id: "+ id));
    }

    private Utente findUserOrThrow(long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "User non trovato con id: "+ id));
    }


}
