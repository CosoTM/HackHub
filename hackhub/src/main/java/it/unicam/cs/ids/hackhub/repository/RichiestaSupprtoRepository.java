package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.RichiestaSupporto;
import it.unicam.cs.ids.hackhub.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RichiestaSupprtoRepository extends JpaRepository<RichiestaSupporto, Long> {
    List<RichiestaSupporto> findByHackathon(Hackathon hackathon);
}
