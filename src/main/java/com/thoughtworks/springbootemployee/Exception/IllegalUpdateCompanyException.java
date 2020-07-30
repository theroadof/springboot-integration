package com.thoughtworks.springbootemployee.Exception;

public class IllegalUpdateCompanyException extends RuntimeException {
    public IllegalUpdateCompanyException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
