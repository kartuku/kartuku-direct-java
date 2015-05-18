/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directclient.model.request;

import com.kartuku.directclient.model.Request;

/**
 *
 * @author mfachri
 */
public class OttRequest extends Request {

    private String cardCVV;
    private String cardExpiry;
    private String cardNumber;
    private String cardToken;
    private String merchantUserCode;
    private String txnAmount;
    private String txnReference;
    private String type;

    public String getMerchantToken() {
        return merchantToken;
    }

    public void setMerchantToken(String merchantToken) {
        this.merchantToken = merchantToken;
    }
    
    /**
     * @return the cardCVV
     */
    public String getCardCVV() {
        return cardCVV;
    }

    /**
     * @param cardCVV the cardCVV to set
     */
    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    /**
     * @return the cardExpiry
     */
    public String getCardExpiry() {
        return cardExpiry;
    }

    /**
     * @param cardExpiry the cardExpiry to set, format <b>YYMM</b>
     */
    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    /**
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the cardToken
     */
    public String getCardToken() {
        return cardToken;
    }

    /**
     * @param cardToken the cardToken to set
     */
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    /**
     * @return the merchantUserCode
     */
    public String getMerchantUserCode() {
        return merchantUserCode;
    }

    /**
     * @param merchantUserCode the merchantUserCode to set
     */
    public void setMerchantUserCode(String merchantUserCode) {
        this.merchantUserCode = merchantUserCode;
    }

    /**
     * @return the txnAmount
     */
    public String getTxnAmount() {
        return txnAmount;
    }

    /**
     * @param txnAmount the txnAmount to set
     */
    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    /**
     * @return the txnReference
     */
    public String getTxnReference() {
        return txnReference;
    }

    /**
     * @param txnReference the txnReference to set
     */
    public void setTxnReference(String txnReference) {
        this.txnReference = txnReference;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set <b>authorize</b>, <b>capture</b>, <b>store</b>
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
}
