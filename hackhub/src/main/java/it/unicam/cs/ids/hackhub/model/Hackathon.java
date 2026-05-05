package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    private float premio;
    private int dimensioneMassimaTeam;

    @Enumerated(EnumType.STRING)
    private StatoHackathon statoHackathon;

    @ManyToOne
    private Utente organizzatore;
    @ManyToOne
    private Utente giudice;
    @ManyToMany
    private List<Utente> mentori;
    @ManyToMany(mappedBy = "hackathonIscritti")
    private List<Team> teamIscritti;
    @ManyToMany
    private List<Utente> utentiEsclusi;

    public Hackathon() {
        this.teamIscritti = new ArrayList<>();
        this.utentiEsclusi = new ArrayList<>();
    }

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

    public float getPremio() {
        return premio;
    }

    public int getDimensioneMassimaTeam() {
        return dimensioneMassimaTeam;
    }

    public StatoHackathon getStatoHackathon() {
        return statoHackathon;
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

    public void setPremio(float premio) {
        this.premio = premio;
    }

    public void setDimensioneMassimaTeam(int dimensioneMassimaTeam) {
        this.dimensioneMassimaTeam = dimensioneMassimaTeam;
    }

    public void setStatoHackathon(StatoHackathon statoHackathon) {
        this.statoHackathon = statoHackathon;
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

    public boolean hasMentore(Utente mentore){
        return mentori.contains(mentore);
    }

    public void addMentore(Utente mentore){
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

    public boolean hasTeamIscritto(Team team){
        return teamIscritti.contains(team);
    }

    public void addTeam(Team team){
        teamIscritti.add(team);
    }

    public void removeTeam(Team team){
        teamIscritti.add(team);
    }

    public List<Utente> getUtentiEsclusi() {
        return utentiEsclusi;
    }

    public void setUtentiEsclusi(List<Utente> utentiEsclusi) {
        this.utentiEsclusi = utentiEsclusi;
    }

    public boolean hasUtenteEscluso(Utente utente){
        return utentiEsclusi.contains(utente);
    }

    public void addUtenteEscluso(Utente utente){
        utentiEsclusi.add(utente);
    }

    public void removeUtenteEscluso(Utente utente){
        utentiEsclusi.remove(utente);
    }

    public boolean isStaff(Utente utente){
        return utente.equals(organizzatore) || utente.equals(giudice) || mentori.contains(utente);
    }
}
