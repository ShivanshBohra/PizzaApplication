package com.example.c3s1pcAuth.service;

import com.example.c3s1pcAuth.domain.Customer;

import java.net.PortUnreachableException;
import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String,String> generateToken(Customer customer);
}
