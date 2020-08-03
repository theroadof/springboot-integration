package com.thoughtworks.springbootemployee.exception;

public class IllegalUpdateCompanyException extends RuntimeException {
    public IllegalUpdateCompanyException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
