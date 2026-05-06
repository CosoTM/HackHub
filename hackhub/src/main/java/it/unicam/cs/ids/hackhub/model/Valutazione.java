package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

@Embeddable
public class Valutazione {
    private String giudizioScritto;
    private int punteggio;

    public String getGiudizioScritto() {
        return giudizioScritto;
    }

    public void setGiudizioScritto(String giudizioScritto) {
        this.giudizioScritto = giudizioScritto;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }
}
