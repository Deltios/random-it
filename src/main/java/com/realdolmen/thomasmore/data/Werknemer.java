package com.realdolmen.thomasmore.data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Werknemer extends User implements Serializable {

    public Werknemer() {
    }

    public Werknemer(String voornaam, String familienaam, String wachtwoord) {
        super(voornaam, familienaam, wachtwoord);
    }

    public Werknemer(String voornaam, String familienaam, int userLevel, String adres, String gemeente, int postcode, String email, String wachtwoord, String telefoon) {
        super(voornaam, familienaam, userLevel, adres, gemeente, postcode, email, wachtwoord, telefoon);
    }
}