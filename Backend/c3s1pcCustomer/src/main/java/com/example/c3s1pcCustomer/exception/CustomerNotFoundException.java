package com.example.c3s1pcCustomer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "This customer is not available")
public class CustomerNotFoundException extends Exception{
}
