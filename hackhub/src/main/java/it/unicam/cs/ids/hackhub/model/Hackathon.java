package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String nome;
    private String regolamento;
    private Date scadenzaIscrizioni;
    private Date dataInizio;
    private Date dataFine;
    private Date scadenzaSottomissioni;
    private String luogo;
    private String premio;
    private int dimensioneMassimaTeam;

    @Enumerated(EnumType.STRING)
    private StatoHackathon cicloDiVita;

    @ManyToOne
    private Utente organizzatore;
    @ManyToOne
    private Utente giudice;
    @ManyToMany
    private List<Utente> mentori;
    @ManyToMany(mappedBy = "hackathonIscritti")
    private List<Team> teamIscritti;


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

    public StatoHackathon getCicloDiVita() {
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

    public void setCicloDiVita(StatoHackathon cicloDiVita) {
        this.cicloDiVita = cicloDiVita;
    }

    public Utente getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(Utente organizzatore) {
        this.organizzatore = organizzatore;
    }

    public Utente getGiudice() {
        return giudice;
    }

    public void setGiudice(Utente giudice) {
        this.giudice = giudice;
    }

    public List<Utente> getMentori() {
        return mentori;
    }

    public void setMentori(List<Utente> mentori) {
        this.mentori = mentori;
    }

    public void addMentore(Utente mentore){
        // TODO: controllare se è mentore?
        mentori.add(mentore);
    }

    public void removeMentore(Utente mentore){
        mentori.remove(mentore);
    }

    public List<Team> getTeamIscritti() {
        return teamIscritti;
    }

    public void setTeamIscritti(List<Team> teamIscritti) {
        this.teamIscritti = teamIscritti;
    }

    public void addTeam(Team team){
        teamIscritti.add(team);
    }

    public void removeTeam(Team team){
        teamIscritti.add(team);
    }



}
