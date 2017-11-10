package com.realdolmen.thomasmore.config;

import com.realdolmen.thomasmore.payment.jaxb.PaymentPort;
import com.realdolmen.thomasmore.payment.jaxb.PaymentPortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    public PaymentPort paymentPort(){
        return new PaymentPortService().getPaymentPortSoap11();
    }

}
