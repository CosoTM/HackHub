package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Violazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViolazioneRepository extends JpaRepository<Violazione, Long> {
    List<Violazione> findByHackathon(Hackathon hackathon);
}
