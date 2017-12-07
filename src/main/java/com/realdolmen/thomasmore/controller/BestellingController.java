package com.realdolmen.thomasmore.controller;


import com.realdolmen.payment.jaxb.PaymentPort;
import com.realdolmen.payment.jaxb.PaymentRequest;
import com.realdolmen.payment.jaxb.PaymentResponse;
import com.realdolmen.thomasmore.data.Bestelling;
import com.realdolmen.thomasmore.data.Product;
import com.realdolmen.thomasmore.service.BestellingService;
import com.realdolmen.thomasmore.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@ManagedBean
@SessionScoped
public class BestellingController {
    @ManagedProperty("#{bestellingService}")
    private BestellingService bestellingService;

    @ManagedProperty("#{paymentService}")
    private PaymentService paymentService;

    public PaymentPort getPaymentPort() {
        return paymentPort;
    }

    public void setPaymentPort(PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    @Autowired
    private PaymentPort paymentPort;

    private String newBestelnummer;
    private LocalDate newBesteldatum;
    private String newOpmerking;

    private String newMerchantId;
    private String newCreditCardNumber;
    private String newCreditCardHolderName;
    private String newCreditCardExpirationDate;
    private String newCvcCode;
    private double newAmount;

    public List<Bestelling> getBestellingen() {
        return bestellingService.findAllBestellingen();
    }

    public String doeBetaling(HttpSession session){
        System.out.println("doeBetaling methode");
        LocalDate vandaag = LocalDate.now();
        System.out.println("vandaag is " + vandaag);
        ProductController productController = new ProductController();

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(productController.berekenTotaalPrijs(session));
        paymentRequest.setCreditCardExpirationDate(newCreditCardExpirationDate);
        UserController userController =  new UserController();
        //session.getAttribute(userController.getNewUserVoornaam());

        paymentRequest.setCreditCardHolderName(newCreditCardHolderName);
        paymentRequest.setCreditCardNumber(newCreditCardNumber);
        paymentRequest.setCvcCode(newCvcCode);
        paymentRequest.setMerchantId("Random IT");
        paymentRequest.setCreditCardHolderName("John Doe");


        //paar controles in console:
        System.out.println("expiration date: " + paymentRequest.getCreditCardExpirationDate());
        System.out.println("totaalprijs: " + paymentRequest.getAmount());
        System.out.println("creditcardnumber: " + paymentRequest.getCreditCardNumber());
        System.out.println("naam klant: " + paymentRequest.getCreditCardHolderName());
        System.out.println("cvc code: " + paymentRequest.getCvcCode());
        System.out.println("merchant ID: " + paymentRequest.getMerchantId());

       PaymentResponse paymentResponse = new PaymentResponse();

        try {
            System.out.println(paymentService.payment(paymentRequest));
            paymentResponse = paymentService.payment(paymentRequest);
            System.out.println("payment opgeslagen in paymentresponse");
        } catch (Exception ex){
            System.out.println("error bij betaling: " + ex);
        }

        System.out.println("paymentresponse: " + paymentResponse);
        System.out.println("is succes?: " +paymentResponse.isSuccess());

        if(paymentResponse.isSuccess()){
            //succespagina
            //createBestelling();
            return "/bestelling/bestelling-bevestiging";
        }

        return "/bestelling/bestelling-mislukt";
    }

    public void createBestelling() {
        LocalDate vandaag = LocalDate.now();
        System.out.println("vandaag is " + vandaag);
        bestellingService.createBestelling(newBestelnummer, vandaag, newOpmerking);
        addMessage("Bestelling toegevoegd!");
        clearForm();
    }

    public String createTestBetaling(HttpSession session){
        System.out.println("in createBestelling");
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setAmount(55);
        paymentRequest.setCreditCardExpirationDate("55/55");
        paymentRequest.setCreditCardHolderName("Marijke Meersman");
        paymentRequest.setCreditCardNumber("2356585852221585");
        paymentRequest.setCvcCode("999");
        paymentRequest.setMerchantId("RandomIT");
        System.out.println("sets zijn gelukt");
        System.out.println(paymentRequest.getAmount());


        PaymentResponse paymentResponse = new PaymentResponse();
        System.out.println("paymentresponse aangemaakt");

        try {
            paymentResponse = paymentService.payment(paymentRequest);
            System.out.println("payment opgeslagen in paymentresponse");
        } catch (NullPointerException ex){
            System.out.println(ex);
        }

        System.out.println(paymentResponse);
        System.out.println(paymentResponse.getErrorMessage());

        if(paymentResponse.isSuccess()) {
            System.out.println("payment success");
            try {
                bestellingService.createBestelling(newBestelnummer,newBesteldatum,newOpmerking);
            } catch (NullPointerException ex){
                System.out.println(ex);
            }

            return "/bestelling/bestelling-bevestiging";
        }
        System.out.println("payment fail");
            return "/bestelling/bestelling-mislukt";
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

    public String getNewMerchantId() {
        return newMerchantId;
    }

    public void setNewMerchantId(String newMerchantId) {
        this.newMerchantId = newMerchantId;
    }

    public String getNewCreditCardNumber() {
        return newCreditCardNumber;
    }

    public void setNewCreditCardNumber(String newCreditCardNumber) {
        this.newCreditCardNumber = newCreditCardNumber;
    }

    public String getNewCreditCardHolderName() {
        return newCreditCardHolderName;
    }

    public void setNewCreditCardHolderName(String newCreditCardHolderName) {
        this.newCreditCardHolderName = newCreditCardHolderName;
    }

    public String getNewCreditCardExpirationDate() {
        return newCreditCardExpirationDate;
    }

    public void setNewCreditCardExpirationDate(String newCreditCardExpirationDate) {
        this.newCreditCardExpirationDate = newCreditCardExpirationDate;
    }

    public String getNewCvcCode() {
        return newCvcCode;
    }

    public void setNewCvcCode(String newCvcCode) {
        this.newCvcCode = newCvcCode;
    }

    public double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(double newAmount) {
        this.newAmount = newAmount;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setBestellingService(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
