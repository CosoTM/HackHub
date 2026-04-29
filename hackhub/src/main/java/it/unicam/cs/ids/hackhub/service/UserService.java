package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Utente createUtente(Utente utente){
        return userRepository.save(utente);
    }

    public Utente getUtenteById(long id) {
        Optional<Utente> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("Utente non trovato " +
                "con id: " + id));
    }

    public Utente getUtenteByName(String name){
        Optional<Utente> user = userRepository.findByNome(name);
        return user.orElseThrow(() -> new ResourceNotFoundException("Utente non trovato " +
                "con nome: " + name));
    }
}
