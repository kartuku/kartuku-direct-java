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
public abstract class Response extends Message {
    protected String ipgGateway;
    protected String ipgResponseCode;
    protected String ipgTxnReference;
    protected String merchantToken;
    protected String respMessage;
    protected String txnIpAddress;
    protected String txnMID;
}
