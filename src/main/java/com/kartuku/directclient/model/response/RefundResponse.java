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
public class RefundResponse extends Response {

    private String txnApprovalCode;
    private String txnAuthAmount;
    private String txnCurrency;
    private String txnRRN;
    private String txnReference;
    private String txnRefundedAmount;
    private String txnResponseCode;

    /**
     * @return the txnApprovalCode
     */
    public String getTxnApprovalCode() {
        return txnApprovalCode;
    }

    /**
     * @param txnApprovalCode the txnApprovalCode to set
     */
    public void setTxnApprovalCode(String txnApprovalCode) {
        this.txnApprovalCode = txnApprovalCode;
    }

    /**
     * @return the txnAuthAmount
     */
    public String getTxnAuthAmount() {
        return txnAuthAmount;
    }

    /**
     * @param txnAuthAmount the txnAuthAmount to set
     */
    public void setTxnAuthAmount(String txnAuthAmount) {
        this.txnAuthAmount = txnAuthAmount;
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
     * @return the txnRRN
     */
    public String getTxnRRN() {
        return txnRRN;
    }

    /**
     * @param txnRRN the txnRRN to set
     */
    public void setTxnRRN(String txnRRN) {
        this.txnRRN = txnRRN;
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
     * @return the txnRefundedAmount
     */
    public String getTxnRefundedAmount() {
        return txnRefundedAmount;
    }

    /**
     * @param txnRefundedAmount the txnRefundedAmount to set
     */
    public void setTxnRefundedAmount(String txnRefundedAmount) {
        this.txnRefundedAmount = txnRefundedAmount;
    }

    /**
     * @return the txnResponseCode
     */
    public String getTxnResponseCode() {
        return txnResponseCode;
    }

    /**
     * @param txnResponseCode the txnResponseCode to set
     */
    public void setTxnResponseCode(String txnResponseCode) {
        this.txnResponseCode = txnResponseCode;
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
