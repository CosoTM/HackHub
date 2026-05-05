package it.unicam.cs.ids.hackhub.model;

import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class RichiestaSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String richiesta;
    private Date dataInvio;
    @Enumerated(EnumType.STRING)
    private StatoRichiesta statoRichiesta;
    @ManyToOne
    private Team team;

    @ManyToOne
    private Hackathon hackathon;

    @OneToMany
    private List<RispostaSupporto> risposte;

    public String getRichiesta() {
        return richiesta;
    }

    public void setRichiesta(String richiesta) {
        this.richiesta = richiesta;
    }

    public Date getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(Date dataInvio) {
        this.dataInvio = dataInvio;
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
