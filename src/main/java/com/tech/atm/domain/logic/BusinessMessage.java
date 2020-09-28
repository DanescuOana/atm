package com.tech.atm.domain.logic;

public class BusinessMessage {

    private String message;
    private Object[] params;

    public BusinessMessage() {
    }

    public BusinessMessage(String message, Object[] params) {
        this.message = message;
        this.params = params;
    }

    public BusinessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
