package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

@Embeddable
public class RispostaSupporto {
    @ManyToOne
    private Utente mentore;
    private String risposta;

    public Utente getMentore() {
        return mentore;
    }

    public void setMentore(Utente mentore) {
        this.mentore = mentore;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }
}
