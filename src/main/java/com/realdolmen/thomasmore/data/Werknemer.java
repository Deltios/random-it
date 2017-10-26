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

<<<<<<< HEAD
    public Werknemer(String voornaam, String familienaam, int userLevel, String adres, String gemeente, int postcode, String email, String wachtwoord, String telefoon) {
        super(voornaam, familienaam, userLevel, adres, gemeente, postcode, email, wachtwoord, telefoon);
=======
    public Werknemer(String voornaam, String familienaam, String adres, String gemeente, String postcode, String email, String wachtwoord, String telefoon) {
        super(voornaam, familienaam, adres, gemeente, postcode, email, wachtwoord, telefoon);
>>>>>>> 56fcaf5011033f7330a255af05a8c2fdf192f272
    }
}