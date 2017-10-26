package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Specificatie;
import com.realdolmen.thomasmore.service.SpecificatieService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@SessionScoped
public class SpecificatieController {
    @ManagedProperty("#{specificatieService}")
    private SpecificatieService specificatieService;

    private String newNaam;
    private String newWaarde;

    public List<Specificatie> getSpecificaties() {
        return specificatieService.findAllSpecificaties();
    }

    public void createSpecificatie() {
        specificatieService.createSpecificatie(newNaam, newWaarde);
        addMessage("Specificatie toegevoegd!");
        clearForm();
    }

    public void createTestSpecificaties(){
        specificatieService.createSpecificatie("Geheugen", "4 GB");
        specificatieService.createSpecificatie("Wifi", "Ja");
        specificatieService.createSpecificatie("HDD", "2 TB");
    }

    private void clearForm() {
        newNaam = null;
        newWaarde = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setSpecificatieService(SpecificatieService specificatieService) {
        this.specificatieService = specificatieService;
    }
}
