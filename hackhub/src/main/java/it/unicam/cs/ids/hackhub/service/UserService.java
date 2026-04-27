package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public long createUtente(Utente utente){
        Utente utenteCreato = userRepository.save(utente);
        return utenteCreato.getID();
    }
}
