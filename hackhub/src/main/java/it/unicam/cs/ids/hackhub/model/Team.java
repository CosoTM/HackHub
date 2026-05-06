package it.unicam.cs.ids.hackhub.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @ManyToMany
    @JsonIgnore
    private List<Hackathon> hackathonIscritti;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

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
