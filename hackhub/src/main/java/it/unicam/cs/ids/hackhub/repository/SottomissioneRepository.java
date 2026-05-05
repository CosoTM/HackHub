package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SottomissioneRepository  extends JpaRepository<Sottomissione, Long> {
    List<Sottomissione> findByHackathon(Hackathon hackathon);
}
