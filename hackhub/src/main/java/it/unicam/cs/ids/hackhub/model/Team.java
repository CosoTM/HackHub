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
    private boolean espulso = false;

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

    public boolean isEspulso() {
        return espulso;
    }

    public void setEspulso(boolean espulso) {
        this.espulso = espulso;
    }

    public Utente getCapoTeam() {return capoTeam;}

    public void setCapoTeam(Utente capoTeam) {this.capoTeam = capoTeam;}

    public List<Utente> getMembriTeam() {return membriTeam;}

    public void setMembriTeam(List<Utente> membriTeam) {this.membriTeam = membriTeam;}

    public boolean hasMembroTeam(Utente utente) {return this.membriTeam.contains(utente);}

    public void addMembroTeam(Utente utente) {this.membriTeam.add(utente);}
    public void removeMembroTeam(Utente utente) {this.membriTeam.remove(utente);}
}
