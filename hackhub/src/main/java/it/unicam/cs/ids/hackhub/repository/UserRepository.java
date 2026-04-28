package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByNome(String nome);
}
