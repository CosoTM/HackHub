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
    private String giudizioScritto;
    private int voto;

    @OneToOne
    private Team team;

    public Date getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(Date dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public String getGiudizioScritto() {
        return giudizioScritto;
    }

    public void setGiudizioScritto(String giudizioScritto) {
        this.giudizioScritto = giudizioScritto;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
