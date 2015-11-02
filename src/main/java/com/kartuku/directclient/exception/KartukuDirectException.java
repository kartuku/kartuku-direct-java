/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kartuku.directclient.exception;

/**
 * Exception related to DirectApi process.
 *
 * @author mfachri
 */
public class KartukuDirectException extends Exception {

    public KartukuDirectException(String message) {
        super(message);
    }

    public KartukuDirectException() {
    }

    public KartukuDirectException(Throwable cause) {
        super(cause);
    }

    public KartukuDirectException(String message, Throwable cause) {
        super(message, cause);
    }
}
