package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Valutazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

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
