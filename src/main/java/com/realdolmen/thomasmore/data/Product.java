package com.realdolmen.thomasmore.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String naam;
	private String omschrijving;
	private double prijs;
	private int hoeveelheidInVoorraad;

	@OneToMany(mappedBy = "product")
	private List<BesteldProduct> besteldeProducten = new ArrayList<>();

	@ManyToOne
	private Merk merk;

	@ManyToMany
	@JoinTable(name = "Product_Categorie")
	private List<Categorie> categorien = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "Product_Specificatie")
	private List<Specificatie> specificaties = new ArrayList<>();


	public Product() {
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

	public String getOmschrijving() {
		return this.omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public double getPrijs() {
		return this.prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public double getHoeveelheidInVoorraad() {
		return this.hoeveelheidInVoorraad;
	}

	public void setHoeveelheidInVoorraad(int hoeveelheidInVoorraad) {
		this.hoeveelheidInVoorraad = hoeveelheidInVoorraad;
	}

	public List<Categorie> getCategorien() {
		return categorien;
	}

	public void setCategorien(List<Categorie> categorien) {
		this.categorien = categorien;
	}

	public Merk getMerk() {
		return merk;
	}

	public void setMerk(Merk merk) {
		this.merk = merk;
	}

	public List<Specificatie> getSpecificaties() {
		return specificaties;
	}

	public void setSpecificaties(List<Specificatie> specificaties) {
		this.specificaties = specificaties;
	}

	public List<BesteldProduct> getBesteldeProducten() {
		return besteldeProducten;
	}

	public void setBesteldeProducten(List<BesteldProduct> besteldeProducten) {
		this.besteldeProducten = besteldeProducten;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * 
	 * @param categorieId
	 */
	public Product getProductenByCategorie(int categorieId) {
		// TODO - implement Product.getProductenByCategorie
		throw new UnsupportedOperationException();
	}

}