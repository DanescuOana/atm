package com.tech.atm.support.exception;

import com.tech.atm.model.logic.BusinessMessage;

import java.util.List;

public class BusinessRuntimeException extends RuntimeException {


    private Object source;

    private Integer code;

    public BusinessRuntimeException() {
        super();
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(Throwable cause) {
        super(cause);
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuntimeException(BusinessMessage m) {
        super();
        this.setSource(m);
    }

    public BusinessRuntimeException(BusinessMessage m, Integer code) {
        super();
        this.setSource(m);
        this.code = code;
    }

    public BusinessRuntimeException(List<BusinessMessage> lm) {
        super();
        this.setSource(lm);
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
