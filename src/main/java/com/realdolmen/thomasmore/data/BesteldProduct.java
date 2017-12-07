package com.realdolmen.thomasmore.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BesteldProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int aantal;
	private double toegepastePrijs;

	@ManyToOne
	private Bestelling bestelling;
	@ManyToOne
	private Product product;

	//constructor
	public BesteldProduct() {
	}


	//getters en setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAantal() {
		return this.aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public double getToegepastePrijs() {
		return this.toegepastePrijs;
	}

	public void setToegepastePrijs(double toegepastePrijs) {
		this.toegepastePrijs = toegepastePrijs;
	}

	public Bestelling getBestelling() {
		return bestelling;
	}

	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}