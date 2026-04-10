package it.unicam.cs.ids.hackhub.model;

import java.util.Date;

public class RichiestaSupporto {
    private String richiesta;
    private Date dataInvio;
    private StatoRichiesta statoRichiesta;
    private Team team;

    public String getRichiesta() {
        return richiesta;
    }

    public void setRichiesta(String richiesta) {
        this.richiesta = richiesta;
    }

    public Date getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(Date dataInvio) {
        this.dataInvio = dataInvio;
    }

    public StatoRichiesta getStatoRichiesta() {
        return statoRichiesta;
    }

    public void setStatoRichiesta(StatoRichiesta statoRichiesta) {
        this.statoRichiesta = statoRichiesta;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
