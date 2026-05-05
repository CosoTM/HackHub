package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

@Entity
public class Violazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Utente mentore;
    @ManyToOne
    private Hackathon hackathon;
    private String motivoSegnalazione;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Utente getMentore() {
        return mentore;
    }

    public void setMentore(Utente mentore) {
        this.mentore = mentore;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public String getMotivoSegnalazione() {
        return motivoSegnalazione;
    }

    public void setMotivoSegnalazione(String motivoSegnalazione) {
        this.motivoSegnalazione = motivoSegnalazione;
    }
}
