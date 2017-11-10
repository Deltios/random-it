//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.realdolmen.thomasmore.payment.jaxb;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(
    name = "PaymentPortService",
    targetNamespace = "http://realdolmen.com/payment/JAXB",
    wsdlLocation = "http://thomasmorejenkins.ddns.net:8090/ws/payment.wsdl"
)
public class PaymentPortService extends Service {
    private static final URL PAYMENTPORTSERVICE_WSDL_LOCATION;
    private static final WebServiceException PAYMENTPORTSERVICE_EXCEPTION;
    private static final QName PAYMENTPORTSERVICE_QNAME = new QName("http://realdolmen.com/payment/JAXB", "PaymentPortService");

    public PaymentPortService() {
        super(__getWsdlLocation(), PAYMENTPORTSERVICE_QNAME);
    }

    public PaymentPortService(WebServiceFeature... var1) {
        super(__getWsdlLocation(), PAYMENTPORTSERVICE_QNAME, var1);
    }

    public PaymentPortService(URL var1) {
        super(var1, PAYMENTPORTSERVICE_QNAME);
    }

    public PaymentPortService(URL var1, WebServiceFeature... var2) {
        super(var1, PAYMENTPORTSERVICE_QNAME, var2);
    }

    public PaymentPortService(URL var1, QName var2) {
        super(var1, var2);
    }

    public PaymentPortService(URL var1, QName var2, WebServiceFeature... var3) {
        super(var1, var2, var3);
    }

    @WebEndpoint(
        name = "PaymentPortSoap11"
    )
    public PaymentPort getPaymentPortSoap11() {
        return (PaymentPort)super.getPort(new QName("http://realdolmen.com/payment/JAXB", "PaymentPortSoap11"), PaymentPort.class);
    }

    @WebEndpoint(
        name = "PaymentPortSoap11"
    )
    public PaymentPort getPaymentPortSoap11(WebServiceFeature... var1) {
        return (PaymentPort)super.getPort(new QName("http://realdolmen.com/payment/JAXB", "PaymentPortSoap11"), PaymentPort.class, var1);
    }

    private static URL __getWsdlLocation() {
        if (PAYMENTPORTSERVICE_EXCEPTION != null) {
            throw PAYMENTPORTSERVICE_EXCEPTION;
        } else {
            return PAYMENTPORTSERVICE_WSDL_LOCATION;
        }
    }

    static {
        URL var0 = null;
        WebServiceException var1 = null;

        try {
            var0 = new URL("http://thomasmorejenkins.ddns.net:8090/ws/payment.wsdl");
        } catch (MalformedURLException var3) {
            var1 = new WebServiceException(var3);
        }

        PAYMENTPORTSERVICE_WSDL_LOCATION = var0;
        PAYMENTPORTSERVICE_EXCEPTION = var1;
    }
}
