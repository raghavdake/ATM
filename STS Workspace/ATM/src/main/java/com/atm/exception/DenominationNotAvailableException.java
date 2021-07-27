package com.atm.exception;

@SuppressWarnings("serial")
public class DenominationNotAvailableException extends Exception {

    public DenominationNotAvailableException(String message) {
        super(message);
    }
}
