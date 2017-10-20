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

	public Klant(String voornaam, String familienaam, String adres, String gemeente, int postcode, String email, String wachtwoord, String telefoon, Calendar datumRegistratie) {
		super(voornaam, familienaam, adres, gemeente, postcode, email, wachtwoord, telefoon);
		this.datumRegistratie = datumRegistratie;
	}

	public Calendar getDatumRegistratie() {
		return datumRegistratie;
	}

	public void setDatumRegistratie(Calendar datumRegistratie) {
		this.datumRegistratie = datumRegistratie;
	}

}