package com.test.error;


//ToDo make this a builder
public class ResourceException {

    private String message;
    private int errorCode;

    public ResourceException(String message) {
        this.message = message;
        this.errorCode = 1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
