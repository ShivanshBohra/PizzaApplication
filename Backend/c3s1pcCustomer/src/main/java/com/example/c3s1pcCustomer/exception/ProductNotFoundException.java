package com.example.c3s1pcCustomer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message) {
        super(message);
}
}

