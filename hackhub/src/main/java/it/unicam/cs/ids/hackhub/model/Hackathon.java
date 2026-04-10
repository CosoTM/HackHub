package it.unicam.cs.ids.hackhub.model;

import java.util.Date;

public class Hackathon {
    private String nome;
    private String regolamento;
    private Date scadenzaIscrizioni;
    private Date dataInizio;
    private Date dataFine;
    private Date scadenzaSottomissioni;
    private String luogo;
    private String premio;
    private int dimensioneMassimaTeam;
    private CicloDiVita cicloDiVita;

    public String getNome() {
        return nome;
    }

    public String getRegolamento() {
        return regolamento;
    }

    public Date getScadenzaIscrizioni() {
        return scadenzaIscrizioni;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public Date getScadenzaSottomissioni() {
        return scadenzaSottomissioni;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getPremio() {
        return premio;
    }

    public int getDimensioneMassimaTeam() {
        return dimensioneMassimaTeam;
    }

    public CicloDiVita getCicloDiVita() {
        return cicloDiVita;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRegolamento(String regolamento) {
        this.regolamento = regolamento;
    }

    public void setScadenzaIscrizioni(Date scadenzaIscrizioni) {
        this.scadenzaIscrizioni = scadenzaIscrizioni;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public void setScadenzaSottomissioni(Date scadenzaSottomissioni) {
        this.scadenzaSottomissioni = scadenzaSottomissioni;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public void setDimensioneMassimaTeam(int dimensioneMassimaTeam) {
        this.dimensioneMassimaTeam = dimensioneMassimaTeam;
    }

    public void setCicloDiVita(CicloDiVita cicloDiVita) {
        this.cicloDiVita = cicloDiVita;
    }
}
