package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    // private Hackathon hackathon;

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
    public void removeMembroTeam(Utente utente) {this.membriTeam.remove(utente);}
}
