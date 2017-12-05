package com.realdolmen.thomasmore.controller;

import com.realdolmen.payment.jaxb.PaymentRequest;
import com.realdolmen.payment.jaxb.PaymentResponse;
import com.realdolmen.thomasmore.data.Bestelling;
import com.realdolmen.thomasmore.service.BestellingService;
import com.realdolmen.thomasmore.service.PaymentService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@ManagedBean
@SessionScoped
public class BestellingController {
    @ManagedProperty("#{bestellingService}")
    private BestellingService bestellingService;

    private String newBestelnummer;
    private LocalDate newBesteldatum;
    private String newOpmerking;

    public List<Bestelling> getBestellingen() {
        return bestellingService.findAllBestellingen();
    }

    public void doeBetaling(){
        PaymentRequest paymentRequest = new PaymentRequest();
        /*paymentRequest.setAmount();
        paymentRequest.setCreditCardExpirationDate();
        paymentRequest.setCreditCardHolderName();
        paymentRequest.setCreditCardNumber();
        paymentRequest.setCvcCode();
        paymentRequest.getMerchantId();*/


        PaymentService paymentService = new PaymentService();
        paymentService.payment(paymentRequest);
        PaymentResponse paymentResponse = new PaymentResponse();

        if(paymentResponse.isSuccess()){
            //succespagina
            createBestelling();
        }else {
            //errorpagina
        }
    }

    public void createBestelling() {
        bestellingService.createBestelling(newBestelnummer, newBesteldatum, newOpmerking);
        addMessage("Bestelling toegevoegd!");
        clearForm();
    }

    public void createTestBestellingen(){
        bestellingService.createBestelling("21548", LocalDate.of(2017,10,20), "Geen");
        bestellingService.createBestelling("21376", LocalDate.of(2017,10,20), "Geen");
        bestellingService.createBestelling("21482", LocalDate.of(2017,10,20), "Geen");
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

    public LocalDate getNewBesteldatum() {
        return newBesteldatum;
    }

    public void setNewBesteldatum(LocalDate newBesteldatum) {
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
