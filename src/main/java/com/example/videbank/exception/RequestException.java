package com.example.videbank.exception;

public class RequestException extends Exception {
    public RequestException(String error){
        super(error);
    }
}
