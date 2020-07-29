package com.thoughtworks.springbootemployee.constant;

public enum ExceptionMessage {
    ILLEGALOPRATION("Illegal Operation"), NO_SUCH_DATA("No Such Data");

    private String errorMsg;

    ExceptionMessage(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
