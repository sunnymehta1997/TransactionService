package org.hcl.hackathon.transactions.constants;

public enum Status {

    SUBMITTED("SUBMITTED"), COMPLETED("COMPLETED"), FAILED("FAILED");

    private String value;

    Status(String value) {
        this.value = value;
    }

}
