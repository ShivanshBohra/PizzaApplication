package com.example.c3s1pcCustomer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Customer alredy exist")
public class CustomerAlredyExistException extends Exception{
}
