package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
}
