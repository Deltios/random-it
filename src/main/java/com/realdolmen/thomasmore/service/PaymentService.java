package com.realdolmen.thomasmore.service;

import com.realdolmen.payment.jaxb.PaymentPort;
import com.realdolmen.payment.jaxb.PaymentRequest;
import com.realdolmen.payment.jaxb.PaymentResponse;
import org.springframework.stereotype.Service;



@Service
public class PaymentService {
    private PaymentPort paymentPort;

    public PaymentResponse payment(PaymentRequest request){
        PaymentResponse response = paymentPort.payment(request);
        return response;
    }
}
