package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Invito;
import it.unicam.cs.ids.hackhub.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitoRepository extends JpaRepository<Invito, Long> {
    List<Invito> findByUtenteInvitato(Utente utente);
}
