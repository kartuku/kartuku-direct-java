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
public class AuthorizeResponse extends Response {

    private String cardExpiry;
    private String cardNumberMasked;
    private String cardToken;
    private String merchantUserCode;
    private String redurectUrl;
    private String txnAmount;
    private String txnApprovalCode;
    private String txnCurrency;
    private String txnCustom1;
    private String txnCustom2;
    private String txnCustom3;
    private String txnRRN;
    private String txnReference;
    private String txnResponseCode;
    private String txnStoreCode;
    private String txnTradingDate;
    private String paymentBrand;

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
     * @return the redurectUrl
     */
    public String getRedurectUrl() {
        return redurectUrl;
    }

    /**
     * @param redurectUrl the redurectUrl to set
     */
    public void setRedurectUrl(String redurectUrl) {
        this.redurectUrl = redurectUrl;
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
     * @return the txnCustom1
     */
    public String getTxnCustom1() {
        return txnCustom1;
    }

    /**
     * @param txnCustom1 the txnCustom1 to set
     */
    public void setTxnCustom1(String txnCustom1) {
        this.txnCustom1 = txnCustom1;
    }

    /**
     * @return the txnCustom2
     */
    public String getTxnCustom2() {
        return txnCustom2;
    }

    /**
     * @param txnCustom2 the txnCustom2 to set
     */
    public void setTxnCustom2(String txnCustom2) {
        this.txnCustom2 = txnCustom2;
    }

    /**
     * @return the txnCustom3
     */
    public String getTxnCustom3() {
        return txnCustom3;
    }

    /**
     * @param txnCustom3 the txnCustom3 to set
     */
    public void setTxnCustom3(String txnCustom3) {
        this.txnCustom3 = txnCustom3;
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
     * @return the txnStoreCode
     */
    public String getTxnStoreCode() {
        return txnStoreCode;
    }

    /**
     * @param txnStoreCode the txnStoreCode to set
     */
    public void setTxnStoreCode(String txnStoreCode) {
        this.txnStoreCode = txnStoreCode;
    }

    /**
     * @return the txnTradingDate
     */
    public String getTxnTradingDate() {
        return txnTradingDate;
    }

    /**
     * @param txnTradingDate the txnTradingDate to set
     */
    public void setTxnTradingDate(String txnTradingDate) {
        this.txnTradingDate = txnTradingDate;
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

    public String getPaymentBrand() {
        return paymentBrand;
    }

    public void setPaymentBrand(String paymentBrand) {
        this.paymentBrand = paymentBrand;
    }
}
