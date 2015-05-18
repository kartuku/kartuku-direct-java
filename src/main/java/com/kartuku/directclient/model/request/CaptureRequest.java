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
public class CaptureRequest extends Request {

    //-- refer to authorize gateway
    private String ipgGateway;

    //-- Consumer unique invoice no
    private String txnReference;
    private String txnAmount;
    private String txnCurrency;

    //-- refer to authorize txnReference
    private String authTxnReference;

    //-- refer to authorize ipgTxnReference
    private String authIpgTxnReference;

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
     * @return the txnCurrency
     */
    public String getTxnCurrency() {
        return txnCurrency;
    }

    /**
     * @param txnCurrency the txnCurrency to set
     */
    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }

    /**
     * @return the authTxnReference
     */
    public String getAuthTxnReference() {
        return authTxnReference;
    }

    /**
     * @param authTxnReference the authTxnReference to set
     */
    public void setAuthTxnReference(String authTxnReference) {
        this.authTxnReference = authTxnReference;
    }

    /**
     * @return the authIpgTxnReference
     */
    public String getAuthIpgTxnReference() {
        return authIpgTxnReference;
    }

    /**
     * @param authIpgTxnReference the authIpgTxnReference to set
     */
    public void setAuthIpgTxnReference(String authIpgTxnReference) {
        this.authIpgTxnReference = authIpgTxnReference;
    }
}
