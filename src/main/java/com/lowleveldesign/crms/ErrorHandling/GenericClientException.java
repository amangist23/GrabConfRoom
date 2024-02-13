package com.lowleveldesign.crms.ErrorHandling;

public class GenericClientException extends RuntimeException{

//    public boolean isClientError;
//    public String message; Can use it handlemultiple exceptions.
    public GenericClientException(String message) {
        super(message);
    }
}
