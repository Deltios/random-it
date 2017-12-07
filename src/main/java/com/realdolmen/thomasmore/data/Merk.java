package com.realdolmen.thomasmore.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Merk implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naam;

	@OneToMany(mappedBy ="merk")
	private List<Product> producten = new ArrayList<>();

	public Merk() {
	}

	public Merk(long id, String naam) {
		this.id = id;
		this.naam = naam;
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

	public List<Product> getProducten() {
		return producten;
	}

	public void setProducten(List<Product> producten) {
		this.producten = producten;
	}
}