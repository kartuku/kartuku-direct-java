/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directclient.model;

/**
 *
 * @author mfachri
 */
public class MessageWrapper extends Message {
    private String message;
    private String messageDigest;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessageDigest(String messageDigest) {
        this.messageDigest = messageDigest;
    }

    public String getMessageDigest() {
        return messageDigest;
    }
}
