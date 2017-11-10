//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.realdolmen.thomasmore.payment.jaxb;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(
    name = "PaymentPort",
    targetNamespace = "http://realdolmen.com/payment/JAXB"
)
@SOAPBinding(
    parameterStyle = ParameterStyle.BARE
)
@XmlSeeAlso({ObjectFactory.class})
public interface PaymentPort {
    @WebMethod
    @WebResult(
        name = "paymentResponse",
        targetNamespace = "http://realdolmen.com/payment/JAXB",
        partName = "paymentResponse"
    )
    PaymentResponse payment(@WebParam(name = "paymentRequest",targetNamespace = "http://realdolmen.com/payment/JAXB",partName = "paymentRequest") PaymentRequest var1);
}
