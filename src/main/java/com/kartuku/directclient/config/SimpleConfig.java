/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directclient.config;

/**
 *
 * @author mfachri
 *
 */
public class SimpleConfig implements Config {

    private String merchantToken = "";
    private String merchantSecretKey = "";

    /**
     * Example of Config class, with basic implementation.
     */
    public SimpleConfig() {
    }

    @Override
    public String getMerchantToken() {
        return merchantToken;
    }

    @Override
    public String getMerchantSecretKey() {
        return merchantSecretKey;
    }

    public void setMerchantSecretKey(String merchantSecretKey) {
        this.merchantSecretKey = merchantSecretKey;
    }

    public void setMerchantToken(String merchantToken) {
        this.merchantToken = merchantToken;
    }

}
