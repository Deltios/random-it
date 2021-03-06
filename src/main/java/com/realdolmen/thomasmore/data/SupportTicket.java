package com.realdolmen.thomasmore.data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
public class SupportTicket implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naam;
	private String opmerking;
	private LocalDate datumAanvraag;
	private String onderwerp;

	@ManyToOne
	private User user;

	public SupportTicket() {
	}

	public SupportTicket(String naam, String opmerking) {
		this.naam = naam;
		this.opmerking = opmerking;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOpmerking() {
		return this.opmerking;
	}

	public void setOpmerking(String opmerking) {
		this.opmerking = opmerking;
	}

	public LocalDate getDatumAanvraag() {
		return datumAanvraag;
	}

	public void setDatumAanvraag(LocalDate datumAanvraag) {
		this.datumAanvraag = datumAanvraag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOnderwerp() {
		return onderwerp;
	}

	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}
}