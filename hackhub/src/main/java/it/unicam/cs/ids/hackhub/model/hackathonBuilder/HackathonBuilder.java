package it.unicam.cs.ids.hackhub.model.hackathonBuilder;

import it.unicam.cs.ids.hackhub.model.Utente;
import java.util.Date;
import java.util.List;

public interface HackathonBuilder {

    void reset();
    void buildNome(String nome);
    void buildRegolamento(String regolamento);
    void buildScadenzaIscrizioni(Date scadenza);
    void buildDataInizio(Date inizio);
    void buildDataFine(Date fine);
    void buildLuogo(String luogo);
    void buildPremio(float premio);
    void buildDimensioneMassimo(int max);
    void buildScadenzaSottomissioni(Date scadenza);
    void buildGiudice(Utente giudice);
    void buildMentori(List<Utente> mentori);
    void buildOrganizzatore(Utente organizzatore);
}