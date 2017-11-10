package com.realdolmen.thomasmore.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Entity
public class Bestelling implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String bestelnummer;
	private String opmerking;
	private Calendar besteldatum;

	@OneToMany(fetch=FetchType.EAGER, mappedBy ="bestelling", cascade = CascadeType.ALL)
	private List<BesteldProduct> besteldeProducten = new ArrayList<>();

	@ManyToOne
	private User user;

	//no arg constructor
	public Bestelling() {
	}

	//getters en setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBestelnummer() {
		return this.bestelnummer;
	}

	public void setBestelnummer(String bestelnummer) {
		this.bestelnummer = bestelnummer;
	}

	public String getOpmerking() {
		return this.opmerking;
	}

	public void setOpmerking(String opmerking) {
		this.opmerking = opmerking;
	}

	public Calendar getBesteldatum() {
		return this.besteldatum;
	}

	public void setBesteldatum(Calendar besteldatum) {
		this.besteldatum = besteldatum;
	}

	public List<BesteldProduct> getBesteldeProducten() {
		return besteldeProducten;
	}

	public void setBesteldeProducten(List<BesteldProduct> besteldeProducten) {
		this.besteldeProducten = besteldeProducten;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 *
	 * @param besteldProduct
	 */
	public double getToegepastePrijs(BesteldProduct besteldProduct) {
		// TODO - implement Bestelling.getToegepastePrijs
		throw new UnsupportedOperationException();
	}


}