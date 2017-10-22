package com.realdolmen.thomasmore.data;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String voornaam;
    private String familienaam;
    private String adres;
    private String gemeente;
    private String postcode;
    private String email;
    private String wachtwoord;
    private String telefoon;

    @OneToMany(mappedBy="user")
    private List<SupportTicket> supportTickets = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<Bestelling> bestellingen = new ArrayList<>();


    //constructors
    public User() {
    }

    public User(String voornaam, String familienaam, String wachtwoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wachtwoord = wachtwoord;
    }

    public User(String voornaam, String familienaam, String adres, String gemeente, String postcode, String email, String wachtwoord, String telefoon) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.adres = adres;
        this.gemeente = gemeente;
        this.postcode = postcode;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.telefoon = telefoon;
    }

    //getters en setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public List<SupportTicket> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicket> supportTickets) {
        this.supportTickets = supportTickets;
    }

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(List<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }
}
