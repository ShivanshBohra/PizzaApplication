package com.example.c3s1pcAuth.service;

import com.example.c3s1pcAuth.domain.Customer;
import com.example.c3s1pcAuth.domain.SignUpData;
import com.example.c3s1pcAuth.exception.CustomerAlredyExistException;
import com.example.c3s1pcAuth.exception.CustomerNotFoundException;

public interface CustomerService {
    public Customer register1(SignUpData signUpData) throws CustomerAlredyExistException;
    public Customer registerCustomer(Customer customer) throws CustomerAlredyExistException;
    public Customer logInCustomer(Customer customer) throws CustomerNotFoundException;
}
