package com.thoughtworks.springbootemployee.Exception;

public class NoSuchEmployeeException extends RuntimeException {
    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
