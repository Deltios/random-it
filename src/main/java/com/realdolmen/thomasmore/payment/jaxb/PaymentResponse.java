//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.realdolmen.thomasmore.payment.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"success", "errorMessage"}
)
@XmlRootElement(
    name = "paymentResponse"
)
public class PaymentResponse {
    protected boolean success;
    protected String errorMessage;

    public PaymentResponse() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean var1) {
        this.success = var1;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String var1) {
        this.errorMessage = var1;
    }
}
