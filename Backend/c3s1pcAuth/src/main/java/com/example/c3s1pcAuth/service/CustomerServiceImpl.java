package com.example.c3s1pcAuth.service;

import com.example.c3s1pcAuth.domain.Customer;
import com.example.c3s1pcAuth.domain.SignUpData;
import com.example.c3s1pcAuth.domain.UserDTO;
import com.example.c3s1pcAuth.exception.CustomerAlredyExistException;
import com.example.c3s1pcAuth.exception.CustomerNotFoundException;
import com.example.c3s1pcAuth.proxy.UserProxy;
import com.example.c3s1pcAuth.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserProxy userProxy;


    @Override
    public Customer register1(SignUpData signUpData) {
        UserDTO userDTO=new UserDTO(signUpData.getEmailId(),signUpData.getName(),signUpData.getAddress(),signUpData.getPhoneNo());
        ResponseEntity<?> responseEntity=userProxy.sendDataToProductApp(userDTO);

        Customer customer=new Customer(signUpData.getEmailId(),signUpData.getPassword(),"Customer");
        return customerRepository.save(customer);
    }

    @Override
    public Customer registerCustomer(Customer customer) throws CustomerAlredyExistException {
        if(customerRepository.findById(customer.getEmailId()).isEmpty())
        {
            return customerRepository.save(customer);
        }
        else {
            throw new CustomerAlredyExistException();
        }

    }

    @Override
    public Customer logInCustomer(Customer customer)throws CustomerNotFoundException {
       if(customerRepository.findById(customer.getEmailId()).isPresent())
       {
        Customer customer1=customerRepository.findById(customer.getEmailId()).get();
        if(customer1.getPassword().equals(customer.getPassword()))
        {
           return customer1;
        }
        else {
            throw new CustomerNotFoundException();
        }
       }
       else {
           return null;
       }
    }
}
