package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String nomeTeam;

    @OneToMany
    private List<Utente> membriTeam = new ArrayList<>();

    @OneToOne
    private Utente capoTeam;

    private int penalizzazioni = 0;

    @ManyToMany
    private List<Hackathon> hackathonIscritti;

    public String getNomeTeam() {
        return nomeTeam;
    }

    public void setNomeTeam(String nomeTeam) {
        this.nomeTeam = nomeTeam;
    }

    public Utente getCapoTeam() {return capoTeam;}

    public void setCapoTeam(Utente utente) {
        if(!hasMembroTeam(utente))
            throw new IllegalArgumentException("Il membro '"+utente.getNome()+
                    "' non fa parte del Team, e non può quindi essere capo.");
        this.capoTeam = utente;
    }

    public List<Utente> getMembriTeam() {return membriTeam;}

    public void setMembriTeam(List<Utente> membriTeam) {this.membriTeam = membriTeam;}

    public boolean hasMembroTeam(Utente utente) {return this.membriTeam.contains(utente);}

    public void addMembroTeam(Utente utente) {this.membriTeam.add(utente);}
    public void removeMembroTeam(Utente utente) {
        this.membriTeam.remove(utente);
        if(utente.equals(capoTeam)){
            // TODO: ???
            capoTeam = null;
            Random r = new Random();
            setCapoTeam(membriTeam.get(r.nextInt(membriTeam.size())));
        }
    }

    public int getPenalizzazioni() {
        return penalizzazioni;
    }

    public void setPenalizzazioni(int penalizzazioni) {
        this.penalizzazioni = penalizzazioni;
    }

    public void penalizza(int punti){
        penalizzazioni -= punti;
    }

    public List<Hackathon> getHackathonIscritti() {
        return hackathonIscritti;
    }

    public void setHackathonIscritti(List<Hackathon> hackathonIscritti) {
        this.hackathonIscritti = hackathonIscritti;
    }

    public void addHackathonIscritto(Hackathon hackathon){
        hackathonIscritti.add(hackathon);
    }

    public void removeHackathonIscritto(Hackathon hackathon){
        hackathonIscritti.remove(hackathon);
    }


}
