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
public class QueryResponse extends Response {

    private boolean txnExists;
    private String txnReference;

    /**
     * @return the txnExists
     */
    public boolean isTxnExists() {
        return txnExists;
    }

    /**
     * @param txnExists the txnExists to set
     */
    public void setTxnExists(boolean txnExists) {
        this.txnExists = txnExists;
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
