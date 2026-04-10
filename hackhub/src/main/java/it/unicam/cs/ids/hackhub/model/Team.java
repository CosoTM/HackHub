package it.unicam.cs.ids.hackhub.model;

public class Team {
    private String nomeTeam;

    public String getNomeTeam() {
        return nomeTeam;
    }

    public void setNomeTeam(String nomeTeam) {
        this.nomeTeam = nomeTeam;
    }

    public boolean isEspulso() {
        return espulso;
    }

    public void setEspulso(boolean espulso) {
        this.espulso = espulso;
    }

    private boolean espulso;

    public Sottomissione inviareSottomissione(){
        return null;
    }

    public RichiestaSupporto inviaRichiestaSupporto(){
        return null;
    }

    public void aggiornareSottomissione(Sottomissione sottomissione){
    }
}
