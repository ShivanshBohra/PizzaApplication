package com.example.c3s1pcAuth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "This customer Id is Not present")
public class CustomerNotFoundException extends Exception {
}
