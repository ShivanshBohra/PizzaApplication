package com.example.c3s1pcCustomer.service;

import com.example.c3s1pcCustomer.domain.Customer;
import com.example.c3s1pcCustomer.domain.Products;
import com.example.c3s1pcCustomer.exception.CustomerAlredyExistException;
import com.example.c3s1pcCustomer.exception.CustomerNotFoundException;
import com.example.c3s1pcCustomer.exception.ProductNotFoundException;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(Customer customer) throws CustomerAlredyExistException;
    public List<Customer> getAllCustomer();
    public boolean deleteCustomerById(String customerName) throws CustomerNotFoundException;
    public Customer updateCustomer(Customer customer);

    public Customer getCustomerdetaild(String email);

    public Products addProduct(Products products);
    public List<Products> getAllProducts(Query query);
    public Customer addProducttocustomer(String email, Products product);

    public List<Products> getProductofcustomer(String email) throws ProductNotFoundException;
    public boolean deleteProductById(String email, String productId) throws ProductNotFoundException;


}
