package com.realdolmen.thomasmore.config;


import com.realdolmen.payment.jaxb.PaymentPort;
import com.realdolmen.payment.jaxb.PaymentPortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    public PaymentPort paymentPort(){
        return null;// new PaymentPortService().getPaymentPortSoap11();
    }

}
