package com.realdolmen.thomasmore.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String naam;

	@ManyToOne
	private Categorie hoofdcategorie;

	@OneToMany(mappedBy = "hoofdcategorie")
	private List<Categorie> subcategories = new ArrayList<>();

	@ManyToMany(mappedBy="categorien")
	private List<Product> producten = new ArrayList<>();


	//constructors
	public Categorie() {
	}

	public Categorie(String naam) {
		this.naam = naam;
	}

	//getters en setters
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

	public Categorie getHoofdcategorie() {
		return hoofdcategorie;
	}

	public void setHoofdcategorie(Categorie hoofdcategorie) {
		this.hoofdcategorie = hoofdcategorie;
	}

	public List<Categorie> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Categorie> subcategories) {
		this.subcategories = subcategories;
	}

	public List<Product> getProducten() {
		return producten;
	}

	public void setProducten(List<Product> producten) {
		this.producten = producten;
	}
}