package it.unicam.cs.ids.hackhub.model.hackathonBuilder;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.StatoHackathon;
import it.unicam.cs.ids.hackhub.model.Utente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConcreteHackathonBuilder implements HackathonBuilder{
    private Hackathon hackathon;

    public ConcreteHackathonBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.hackathon = new Hackathon();
        this.hackathon.setStatoHackathon(StatoHackathon.ISCRIZIONE);
    }

    @Override
    public void buildNome(String nome) {
        hackathon.setNome(nome);
    }

    @Override
    public void buildRegolamento(String regolamento) {
        hackathon.setRegolamento(regolamento);
    }

    @Override
    public void buildScadenzaIscrizioni(Date scadenza) {
        hackathon.setScadenzaIscrizioni(scadenza);
    }

    @Override
    public void buildDataInizio(Date inizio) {
        hackathon.setDataInizio(inizio);
    }

    @Override
    public void buildDataFine(Date fine) {
        hackathon.setDataFine(fine);
    }

    @Override
    public void buildLuogo(String luogo) {
        hackathon.setLuogo(luogo);
    }

    @Override
    public void buildPremio(float premio) {
        hackathon.setPremio(premio);
    }

    @Override
    public void buildDimensioneMassimo(int max) {
        hackathon.setDimensioneMassimaTeam(max);
    }

    @Override
    public void buildScadenzaSottomissioni(Date scadenza) {
        hackathon.setScadenzaSottomissioni(scadenza);
    }

    @Override
    public void buildGiudice(Utente giudice) {
        hackathon.setGiudice(giudice);
    }

    @Override
    public void buildMentori(List<Utente> mentori) {
        hackathon.setMentori(mentori);
    }

    @Override
    public void buildOrganizzatore(Utente organizzatore) {
        hackathon.setOrganizzatore(organizzatore);
    }

    public Hackathon getResult(){
        return this.hackathon;
    }
}
