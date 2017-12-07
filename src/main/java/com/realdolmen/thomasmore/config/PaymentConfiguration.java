package com.realdolmen.thomasmore.config;


import com.realdolmen.payment.jaxb.PaymentPort;
import com.realdolmen.payment.jaxb.PaymentPortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    public PaymentPort paymentPort(){
        try {
            return new PaymentPortService().getPaymentPortSoap11();
        } catch (RuntimeException ex){
            System.out.println("paymentPort configuration is mislukt");
            return null;
        }

    }

}
