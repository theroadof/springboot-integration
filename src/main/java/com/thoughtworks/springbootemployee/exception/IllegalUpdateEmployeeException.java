package com.thoughtworks.springbootemployee.exception;

public class IllegalUpdateEmployeeException extends RuntimeException {
    public IllegalUpdateEmployeeException(String message) {
        super(message);
    }
}
