package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Invito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private Team teamInvitante;
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
