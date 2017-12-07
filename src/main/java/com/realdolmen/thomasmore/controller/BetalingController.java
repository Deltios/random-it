package com.realdolmen.thomasmore.controller;


import com.realdolmen.payment.jaxb.PaymentRequest;
import com.realdolmen.payment.jaxb.PaymentResponse;
import com.realdolmen.thomasmore.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

public class BetalingController {
    @Autowired
    private PaymentService paymentService;

    public String doeBetaling(){
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
            //BestellingController.createBestelling(paymentRequest.getMerchantId());
        }else {
            //errorpagina
        }

        return "/index";
    }

}
