package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Categorie;
import com.realdolmen.thomasmore.service.CategorieService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@SessionScoped
public class CategorieController {
    @ManagedProperty("#{categorieService}")
    private CategorieService categorieService;

    private String newNaam;

    public List<Categorie> getCategories() {
        return categorieService.findAllCategories();
    }

    public void createCategorie() {
        categorieService.createCategorie(newNaam);
        addMessage("Categorie toegevoegd!");
        clearForm();
    }

    public void createTestCategories(){
        categorieService.createCategorie("CPU");
        categorieService.createCategorie("GPU");
        categorieService.createCategorie("Moederbord");
        categorieService.createCategorie("Voeding");
        categorieService.createCategorie("RAM");
        categorieService.createCategorie("Netwerkkaart");
    }

    private void clearForm() {
        newNaam = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public CategorieService getCategorieService() {
        return categorieService;
    }

    public String getNewNaam() {
        return newNaam;
    }

    public void setNewNaam(String newNaam) {
        this.newNaam = newNaam;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setCategorieService(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
}
