/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directclient.exception;

/**
 * Exception related to signature validation.
 *
 * @author mfachri
 */
public class NoSignatureException extends KartukuDirectException {

    public NoSignatureException() {
    }

    public NoSignatureException(String message) {
        super(message);
    }

    public NoSignatureException(Throwable cause) {
        super(cause);
    }

    public NoSignatureException(String message, Throwable cause) {
        super(message, cause);
    }

}
