package com.thoughtworks.springbootemployee.exception;

public class NoSuchCompanyException extends RuntimeException {
    public NoSuchCompanyException(String message) {
        super(message);
    }
}
