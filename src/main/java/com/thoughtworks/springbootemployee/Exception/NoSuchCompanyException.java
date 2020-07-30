package com.thoughtworks.springbootemployee.Exception;

public class NoSuchCompanyException extends RuntimeException {
    public NoSuchCompanyException(String message) {
        super(message);
    }
}
