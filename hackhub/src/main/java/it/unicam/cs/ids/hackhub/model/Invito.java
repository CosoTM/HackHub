package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

@Entity
public class Invito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @ManyToOne
    private Team teamInvitante;
    @ManyToOne
    private Utente utenteInvitato;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Team getTeamInvitante() {
        return teamInvitante;
    }

    public void setTeamInvitante(Team teamInvitante) {
        this.teamInvitante = teamInvitante;
    }

    public Utente getUtenteInvitato() {
        return utenteInvitato;
    }

    public void setUtenteInvitato(Utente utenteInvitato) {
        this.utenteInvitato = utenteInvitato;
    }

}
