package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByHackathonIscritti_ID(Long hackathonID);
}
