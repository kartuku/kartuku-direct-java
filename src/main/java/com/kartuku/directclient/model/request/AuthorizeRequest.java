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
public class AuthorizeRequest extends Request{
    
    private String merchantUserCode;
    private String txnTradingDate;
    private String txnStoreCode;

    //-- Consumer unique invoice no
    private String txnReference;

    private String txnAmount;
    private String txnCurrency;
    private String txnCustom1;
    private String txnCustom2;
    private String txnCustom3;

    //-- set to true to tokenize given card details (via OTT) for future transaction.
    private Boolean cardTokenize;

    //-- OTT / card Token
    private String cardToken;

    //-- only if using Tokenization Token instead of OTT
    private String cardCVV;    
    //-- bin filtering, separated by colon ':'.
    private String filterBin;
    //-- card type filtering, separated by colon ':'.
    private String filterCard;

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
     * @return the txnTradingDate
     */
    public String getTxnTradingDate() {
        return txnTradingDate;
    }

    /**
     * @param txnTradingDate the txnTradingDate to set format YYYY-MM-DD HH:mm:ss example 2015-04-24 09:46:13
     */
    public void setTxnTradingDate(String txnTradingDate) {
        this.txnTradingDate = txnTradingDate;
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
     * @return the cardTokenize
     */
    public boolean isCardTokenize() {
        return cardTokenize;
    }

    /**
     * @param cardTokenize the cardTokenize to set
     */
    public void setCardTokenize(Boolean cardTokenize) {
        this.cardTokenize = cardTokenize;
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
     * @return the filterBin
     */
    public String getFilterBin() {
        return filterBin;
    }

    /**
     * @param filterBin the filterBin to set
     */
    public void setFilterBin(String filterBin) {
        this.filterBin = filterBin;
    }

    /**
     * @return the filterCard
     */
    public String getFilterCard() {
        return filterCard;
    }

    /**
     * @param filterCard the filterCard to set
     */
    public void setFilterCard(String filterCard) {
        this.filterCard = filterCard;
    }
}
