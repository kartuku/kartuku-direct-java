/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directclient.model;

/**
 * @author mfachri
 */
public class CardToken {
    private String cardToken;
    private String cardNumberMasked;
    private String cardExpiry;

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
}
