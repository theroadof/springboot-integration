package com.thoughtworks.springbootemployee.constant;

public enum ExceptionMessage {

    ILLEGAL_UPDATE_COMPANY("Illegally Update Company"),
    ILLEGAL_UPDATE_EMPLOYEE("Illegally Update Employee"),
    NO_SUCH_COMPANY("No Such Company"),
    NO_SUCH_EMPLOYEE("No Such Employee");

    private String errorMsg;

    ExceptionMessage(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
