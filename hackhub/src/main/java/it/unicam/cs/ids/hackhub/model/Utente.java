package it.unicam.cs.ids.hackhub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String nome;
    private String email;
    private String password;

    @ElementCollection(targetClass = UtenteType.class)
    @Enumerated(EnumType.STRING)
    private List<UtenteType> tipoUtente = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Team team;

    public long getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UtenteType> getTipoUtente(){ return tipoUtente;}
    public void setTipoUtente(List<UtenteType> tipoUtente) {this.tipoUtente = tipoUtente;}

    public boolean hasTipoUtente(UtenteType tipo){return tipoUtente.contains(tipo);}
    public void addTipoUtente(UtenteType tipo){tipoUtente.add(tipo);}
    public void removeTipoUtente(UtenteType tipo){tipoUtente.remove(tipo);}

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
