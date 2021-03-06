
package com.realdolmen.payment.jaxb;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PaymentPort", targetNamespace = "http://realdolmen.com/payment/JAXB")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PaymentPort {


    /**
     * 
     * @param paymentRequest
     * @return
     *     returns com.realdolmen.payment.jaxb.PaymentResponse
     */
    @WebMethod
    @WebResult(name = "paymentResponse", targetNamespace = "http://realdolmen.com/payment/JAXB", partName = "paymentResponse")
    public PaymentResponse payment(
        @WebParam(name = "paymentRequest", targetNamespace = "http://realdolmen.com/payment/JAXB", partName = "paymentRequest")
        PaymentRequest paymentRequest);

}
