package com.example.c3s1pcAuth.controller;

import com.example.c3s1pcAuth.domain.Customer;
import com.example.c3s1pcAuth.domain.SignUpData;
import com.example.c3s1pcAuth.exception.CustomerAlredyExistException;
import com.example.c3s1pcAuth.exception.CustomerNotFoundException;
import com.example.c3s1pcAuth.service.CustomerService;
import com.example.c3s1pcAuth.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    //http://localhost:8807/api/customer/register
    @PostMapping("/register")
    public ResponseEntity<?> registercust(@RequestBody SignUpData signUpData) throws CustomerAlredyExistException
    {
        try {
            System.out.println("hello i am running");
            return new ResponseEntity<>(customerService.register1(signUpData), HttpStatus.OK);
        }
        catch (CustomerAlredyExistException customerAlredyExistException)
        {
            throw  customerAlredyExistException;
        }
    }

    //http://localhost:8807/api/customer/login
    @PostMapping("/login")
    public ResponseEntity<?> logInCustomer(@RequestBody Customer customer) throws CustomerNotFoundException
    {
        try {
            Customer checkedCustomer=customerService.logInCustomer(customer);
            if(checkedCustomer!=null)
            {
                return new ResponseEntity<>(securityTokenGenerator.generateToken(checkedCustomer),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Authonthication Failed....",HttpStatus.NOT_FOUND);
            }
        }
       catch (CustomerNotFoundException customerNotFoundException)
       {
           throw customerNotFoundException;
       }

    }
}
