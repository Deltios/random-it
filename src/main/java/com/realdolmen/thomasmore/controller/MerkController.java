package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Merk;
import com.realdolmen.thomasmore.service.MerkService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped
public class MerkController {
    @ManagedProperty("#{merkService}")
    private MerkService merkService;

    private String newNaam;

    public List<Merk> getMerken() {
        return merkService.findAllMerken();
    }

    public void createMerk() {
        merkService.createMerk(newNaam);
        addMessage("Merk toegevoegd!");
        clearForm();
    }


    public void createTestMerken(){
        merkService.createMerk("Intel");
        merkService.createMerk("Nvidia");
        merkService.createMerk("Gigabyte");
    }

    private void clearForm() {
        newNaam = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setMerkService(MerkService merkService) {
        this.merkService = merkService;
    }
}
