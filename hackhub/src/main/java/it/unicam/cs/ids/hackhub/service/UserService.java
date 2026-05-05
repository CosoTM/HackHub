package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.exception.api.AlreadyExistsException;
import it.unicam.cs.ids.hackhub.exception.api.ResourceNotFoundException;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Utente findUserOrThrow(long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Utente non trovato " + "con id: " + id));
    }

    private Utente findUserOrThrow(String name){
        return userRepository.findByNome(name).orElseThrow(() -> new ResourceNotFoundException(
                "Utente non trovato " + "con nome: " + name));
    }

    public Utente registraUtente(String nome, String email, String password){

        Utente esistente = userRepository.findByEmail(email).orElse(null);
        if(esistente == null)
            throw new AlreadyExistsException("Un utente con la stessa email esiste già. Inseriscine un'altra");

        Utente registrato = new Utente();
        registrato.setNome(nome);
        registrato.setEmail(email);
        registrato.setPassword(password);

        return userRepository.save(registrato);
    }

    public Utente getUtenteById(long id) {
        return findUserOrThrow(id);
    }

    public Utente getUtenteByName(String name){
        return findUserOrThrow(name);
    }
}
