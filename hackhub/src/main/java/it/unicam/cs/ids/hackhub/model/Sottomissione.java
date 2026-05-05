package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.io.File;
import java.util.Date;

@Entity
public class Sottomissione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    // private File file;
    private Date dataConsegna;
    @OneToOne
    private Valutazione valutazione;
    private boolean inviata;
    @OneToOne
    private Team team;
    @OneToOne
    private Hackathon hackathon;

    public Date getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(Date dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public Valutazione getValutazione() {
        return valutazione;
    }

    public void setValutazione(Valutazione valutazione) {
        this.valutazione = valutazione;
    }

    public boolean isInviata() {
        return inviata;
    }

    public void setInviata(boolean inviata) {
        this.inviata = inviata;
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
}
