package com.thoughtworks.springbootemployee.Exception;

public class IllegalUpdateEmployeeException extends RuntimeException {
    public IllegalUpdateEmployeeException(String message) {
        super(message);
    }
}
