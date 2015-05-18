/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directclient.config;

/**
 *
 * @author mfachri
 */
public interface Config {

    /**
     * Return token used to identify merchant.
     *
     * @return
     */
    public String getMerchantToken();

    /**
     * Return secret key used to sign request & response. This is supposed to be
     * secret. DO NOT STORE THIS ON YOUR CLIENT SIDE.
     *
     * @return
     */
    public String getMerchantSecretKey();
}
