package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
public class RichiestaSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String titolo;
    private String corpo;
    @Enumerated(EnumType.STRING)
    private StatoRichiesta statoRichiesta;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hackathon hackathon;

    @ElementCollection
    private List<RispostaSupporto> risposte;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public StatoRichiesta getStatoRichiesta() {
        return statoRichiesta;
    }

    public void setStatoRichiesta(StatoRichiesta statoRichiesta) {
        this.statoRichiesta = statoRichiesta;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public List<RispostaSupporto> getRisposte() {
        return risposte;
    }

    public void setRisposte(List<RispostaSupporto> risposte) {
        this.risposte = risposte;
    }

    public void addRisposta(RispostaSupporto risposta){
        risposte.add(risposta);
    }
}
