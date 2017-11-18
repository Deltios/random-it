package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Product;
import com.realdolmen.thomasmore.service.ProductService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@SessionScoped
public class ProductController {
    @ManagedProperty("#{productService}")
    private ProductService productService;

    private String newNaam;
    private String newOmschrijving;
    private double newPrijs;
    private int newHoeveelheidInVoorraad;

    private Product product;
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    private Long productId;
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
        this.product = productService.getProduct(productId);
    }

    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    public void createProduct() {
        productService.createProduct(newNaam, newOmschrijving, newPrijs, newHoeveelheidInVoorraad);
        addMessage("Product toegevoegd!");
        clearForm();
    }

    public void createTestProducten(){
        productService.createProduct("GTX 1080", "Zotte GPU", 899, 82);
        productService.createProduct("i7 6700k", "Zotte CPU", 499, 64);
        productService.createProduct("Z370 Aorus", "Zot moederbordje", 279, 38);
    }

    private void clearForm() {
        newNaam = null;
        newOmschrijving = null;
        newPrijs = 0;
        newHoeveelheidInVoorraad = 0;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Aanpassen product
    public String editProduct(Product product) {

        return null;
    }

    public ProductService getProductService() {
        return productService;
    }

    public String getNewNaam() {
        return newNaam;
    }

    public void setNewNaam(String newNaam) {
        this.newNaam = newNaam;
    }

    public String getNewOmschrijving() {
        return newOmschrijving;
    }

    public void setNewOmschrijving(String newOmschrijving) {
        this.newOmschrijving = newOmschrijving;
    }

    public double getNewPrijs() {
        return newPrijs;
    }

    public void setNewPrijs(double newPrijs) {
        this.newPrijs = newPrijs;
    }

    public int getNewHoeveelheidInVoorraad() {
        return newHoeveelheidInVoorraad;
    }

    public void setNewHoeveelheidInVoorraad(int newHoeveelheidInVoorraad) {
        this.newHoeveelheidInVoorraad = newHoeveelheidInVoorraad;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
