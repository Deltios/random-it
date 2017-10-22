package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Bestelling;
import com.realdolmen.thomasmore.service.BestellingService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@ManagedBean
@ViewScoped
public class BestellingController {
    @ManagedProperty("#{bestellingService}")
    private BestellingService bestellingService;

    private String newBestelnummer;
    private Calendar newBesteldatum;
    private String newOpmerking;

    public List<Bestelling> getBestellingen() {
        return bestellingService.findAllBestellingen();
    }

    public void createBestelling() {
        bestellingService.createBestelling(newBestelnummer, newBesteldatum, newOpmerking);
        addMessage("Bestelling toegevoegd!");
        clearForm();
    }

    public void createTestBestellingen(){
        bestellingService.createBestelling("21548", new GregorianCalendar(2017,10,20), "Geen");
        bestellingService.createBestelling("21376", new GregorianCalendar(2017,10,20), "Geen");
        bestellingService.createBestelling("21482", new GregorianCalendar(2017,10,20), "Geen");
    }

    private void clearForm() {
        newBestelnummer = null;
        newBesteldatum = null;
        newOpmerking = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public BestellingService getBestellingService() {
        return bestellingService;
    }

    public String getNewBestelnummer() {
        return newBestelnummer;
    }

    public void setNewBestelnummer(String newBestelnummer) {
        this.newBestelnummer = newBestelnummer;
    }

    public Calendar getNewBesteldatum() {
        return newBesteldatum;
    }

    public void setNewBesteldatum(Calendar newBesteldatum) {
        this.newBesteldatum = newBesteldatum;
    }

    public String getNewOpmerking() {
        return newOpmerking;
    }

    public void setNewOpmerking(String newOpmerking) {
        this.newOpmerking = newOpmerking;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setBestellingService(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }
}
