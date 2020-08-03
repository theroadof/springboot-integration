package com.thoughtworks.springbootemployee.config;

import com.thoughtworks.springbootemployee.exception.*;
import com.thoughtworks.springbootemployee.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchEmployeeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleNoSuchDataException() {
        return ExceptionMessage.NO_SUCH_EMPLOYEE.getErrorMsg();
    }

    @ExceptionHandler(NoSuchCompanyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleIllegalOperationException() {
        return ExceptionMessage.NO_SUCH_COMPANY.getErrorMsg();
    }

    @ExceptionHandler(IllegalUpdateEmployeeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String handleIllegalUpdateEmployeeException() {
        return ExceptionMessage.ILLEGAL_UPDATE_EMPLOYEE.getErrorMsg();
    }

    @ExceptionHandler(IllegalUpdateCompanyException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String handleIllegalUpdateCompanyException() {
        return ExceptionMessage.ILLEGAL_UPDATE_COMPANY.getErrorMsg();
    }
}
