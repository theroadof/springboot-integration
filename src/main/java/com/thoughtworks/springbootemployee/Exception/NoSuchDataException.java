package com.thoughtworks.springbootemployee.Exception;

public class NoSuchDataException extends RuntimeException{
    public NoSuchDataException(String message) {
        super(message);
    }
}
