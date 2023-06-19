package com.example.c3s1pcCustomer.service;

import com.example.c3s1pcCustomer.domain.Customer;
import com.example.c3s1pcCustomer.domain.Products;
import com.example.c3s1pcCustomer.exception.CustomerNotFoundException;
import com.example.c3s1pcCustomer.exception.ProductNotFoundException;
import com.example.c3s1pcCustomer.repository.CustomerRepository;
import com.example.c3s1pcCustomer.repository.productRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private productRepositery productrepositery;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.insert(customer);
    }

    @Override
    public Products addProduct(Products products) {
        return productrepositery.insert(products);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Products> getAllProducts(Query query) {
        return productrepositery.findAll();
    }


    @Override
    public Customer updateCustomer(Customer customer)
    {
        Customer c1 = customerRepository.findById(customer.getEmailId()).get();
        c1.setName(customer.getName());
        c1.setAddress(customer.getAddress());
        c1.setPhoneNo(customer.getPhoneNo());
        customerRepository.deleteById(customer.getEmailId());
        return customerRepository.insert(c1);
    }

    @Override
    public Customer getCustomerdetaild(String email) {
        Customer c1= customerRepository.findById(email).get();
        return c1;
    }


    @Override
    public boolean deleteCustomerById(String customerName) throws CustomerNotFoundException {
        if(customerRepository.findById(customerName).isPresent())
        {
            customerRepository.deleteById(customerName);
            return true;
        }
        else {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public Customer addProducttocustomer(String email, Products product) {
        Customer user = customerRepository.findById(email).get();
        user.getProducts().add(product);
        return customerRepository.save(user);
    }
    @Override
    public List<Products> getProductofcustomer(String email) {
        Customer user = customerRepository.findById(email).get();
        List<Products> p1 = user.getProducts();
        return p1;
    }


    @Override
    public boolean deleteProductById(String email, String productId) throws ProductNotFoundException {
        Customer customer = customerRepository.findById(email).orElseThrow(() -> new ProductNotFoundException("customer not found with email : " + email));
        List<Products> products = customer.getProducts();
        products.removeIf(p -> p.getProductId().equals(productId));
        customer.setProducts(products);
        customerRepository.save(customer);
        return true;
    }


}
