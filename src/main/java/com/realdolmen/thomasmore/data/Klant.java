package com.realdolmen.thomasmore.data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Calendar;


@Entity
public class Klant extends User implements Serializable {

	private Calendar datumRegistratie;

	public Klant() {
	}

	public Klant(String voornaam, String familienaam, String wachtwoord, Calendar datumRegistratie) {
		super(voornaam, familienaam, wachtwoord);
		this.datumRegistratie = datumRegistratie;
	}

<<<<<<< HEAD
	public Klant(String voornaam, String familienaam, int userLevel,String adres, String gemeente, int postcode, String email, String wachtwoord, String telefoon, Calendar datumRegistratie) {
		super(voornaam, familienaam, userLevel, adres, gemeente, postcode, email, wachtwoord, telefoon);
=======
	public Klant(String voornaam, String familienaam, String adres, String gemeente, String postcode, String email, String wachtwoord, String telefoon, Calendar datumRegistratie) {
		super(voornaam, familienaam, adres, gemeente, postcode, email, wachtwoord, telefoon);
>>>>>>> 56fcaf5011033f7330a255af05a8c2fdf192f272
		this.datumRegistratie = datumRegistratie;
	}

	public Calendar getDatumRegistratie() {
		return datumRegistratie;
	}

	public void setDatumRegistratie(Calendar datumRegistratie) {
		this.datumRegistratie = datumRegistratie;
	}

}