/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kartuku.directclient.model.Response;

/**
 * @author mfachri
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenStoreResponse extends Response {

    private String cardExpiry;
    private String cardNumberMasked;
    private String cardToken;
    private String merchantUserCode;

    /**
     * @return the cardExpiry
     */
    public String getCardExpiry() {
        return cardExpiry;
    }

    /**
     * @param cardExpiry the cardExpiry to set
     */
    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    /**
     * @return the cardNumberMasked
     */
    public String getCardNumberMasked() {
        return cardNumberMasked;
    }

    /**
     * @param cardNumberMasked the cardNumberMasked to set
     */
    public void setCardNumberMasked(String cardNumberMasked) {
        this.cardNumberMasked = cardNumberMasked;
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
     * @return the ipgGateway
     */
    public String getIpgGateway() {
        return ipgGateway;
    }

    /**
     * @param ipgGateway the ipgGateway to set
     */
    public void setIpgGateway(String ipgGateway) {
        this.ipgGateway = ipgGateway;
    }

    /**
     * @return the ipgResponseCode
     */
    public String getIpgResponseCode() {
        return ipgResponseCode;
    }

    /**
     * @param ipgResponseCode the ipgResponseCode to set
     */
    public void setIpgResponseCode(String ipgResponseCode) {
        this.ipgResponseCode = ipgResponseCode;
    }

    /**
     * @return the ipgTxnReference
     */
    public String getIpgTxnReference() {
        return ipgTxnReference;
    }

    /**
     * @param ipgTxnReference the ipgTxnReference to set
     */
    public void setIpgTxnReference(String ipgTxnReference) {
        this.ipgTxnReference = ipgTxnReference;
    }

    /**
     * @return the merchantToken
     */
    public String getMerchantToken() {
        return merchantToken;
    }

    /**
     * @param merchantToken the merchantToken to set
     */
    public void setMerchantToken(String merchantToken) {
        this.merchantToken = merchantToken;
    }

    /**
     * @return the respMessage
     */
    public String getRespMessage() {
        return respMessage;
    }

    /**
     * @param respMessage the respMessage to set
     */
    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    /**
     * @return the txnIpAddress
     */
    public String getTxnIpAddress() {
        return txnIpAddress;
    }

    /**
     * @param txnIpAddress the txnIpAddress to set
     */
    public void setTxnIpAddress(String txnIpAddress) {
        this.txnIpAddress = txnIpAddress;
    }

    /**
     * @return the txnMID
     */
    public String getTxnMID() {
        return txnMID;
    }

    /**
     * @param txnMID the txnMID to set
     */
    public void setTxnMID(String txnMID) {
        this.txnMID = txnMID;
    }
}
