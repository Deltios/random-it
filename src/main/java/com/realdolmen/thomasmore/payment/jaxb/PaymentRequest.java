//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.realdolmen.thomasmore.payment.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"merchantId", "creditCardNumber", "creditCardHolderName", "creditCardExpirationDate", "cvcCode", "amount"}
)
@XmlRootElement(
    name = "paymentRequest"
)
public class PaymentRequest {
    @XmlElement(
        required = true
    )
    protected String merchantId;
    @XmlElement(
        required = true
    )
    protected String creditCardNumber;
    @XmlElement(
        required = true
    )
    protected String creditCardHolderName;
    @XmlElement(
        required = true
    )
    protected String creditCardExpirationDate;
    @XmlElement(
        required = true
    )
    protected String cvcCode;
    protected double amount;

    public PaymentRequest() {
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String var1) {
        this.merchantId = var1;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public void setCreditCardNumber(String var1) {
        this.creditCardNumber = var1;
    }

    public String getCreditCardHolderName() {
        return this.creditCardHolderName;
    }

    public void setCreditCardHolderName(String var1) {
        this.creditCardHolderName = var1;
    }

    public String getCreditCardExpirationDate() {
        return this.creditCardExpirationDate;
    }

    public void setCreditCardExpirationDate(String var1) {
        this.creditCardExpirationDate = var1;
    }

    public String getCvcCode() {
        return this.cvcCode;
    }

    public void setCvcCode(String var1) {
        this.cvcCode = var1;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double var1) {
        this.amount = var1;
    }
}
