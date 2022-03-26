package org.hcl.hackathon.transactions.exceptions;

public class TransactionNotFoundException extends RuntimeException {

    private final String errorMessage;

    public TransactionNotFoundException(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
