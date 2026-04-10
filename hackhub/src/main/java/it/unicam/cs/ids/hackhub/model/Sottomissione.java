package it.unicam.cs.ids.hackhub.model;

import java.io.File;
import java.util.Date;

public class Sottomissione {
    private File file;
    private Date dataConsegna;
    private String giudizioScritto;
    private int voto;
    private Team team;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

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
