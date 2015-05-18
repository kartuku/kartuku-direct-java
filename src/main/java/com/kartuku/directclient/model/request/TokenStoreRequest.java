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
public class TokenStoreRequest extends Request {

    private String merchantUserCode;
    //-- OTT Token
    private String cardToken;

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
}
