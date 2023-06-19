package com.example.c3s1pcCustomer.controller;

import com.example.c3s1pcCustomer.domain.Admin;
import com.example.c3s1pcCustomer.domain.Customer;
import com.example.c3s1pcCustomer.domain.Products;
import com.example.c3s1pcCustomer.exception.CustomerAlredyExistException;
import com.example.c3s1pcCustomer.exception.CustomerNotFoundException;
import com.example.c3s1pcCustomer.exception.ProductNotFoundException;
import com.example.c3s1pcCustomer.service.CustomerService;
import com.mongodb.client.model.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CustomerController {

    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService=customerService;
    }

    //http://localhost:8805/api/v1/add
    @PostMapping("/add")
    public ResponseEntity<?> addCustomers(@RequestBody Customer customer) throws CustomerAlredyExistException
    {
        try {

            customer.setProducts(new ArrayList<Products>());
            return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
        }
       catch (CustomerAlredyExistException customerAlredyExistException)
       {
           throw customerAlredyExistException;
       }
    }

    //http://localhost:8805/api/v1/get
    @GetMapping("/get")
    public List<Customer> getAllCustomers(HttpServletRequest httpServletRequest)
    {
        return customerService.getAllCustomer();
    }


    //http://localhost:8805/api/v1/get
    @GetMapping("/getproducts")
    public List<Products> getAllProducts(HttpServletRequest httpServletRequest)
    {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "price"));
        return customerService.getAllProducts(query);
    }
    //http://localhost:8805/api/v1/delete/*
    @DeleteMapping("/delete/{customerName}")
    public ResponseEntity<?> deleteBYName(@PathVariable String customerName,HttpServletRequest httpServletRequest)throws CustomerNotFoundException
    {
        System.out.println("hi");
        try {
            System.out.println(customerName);
            return new ResponseEntity<>(customerService.deleteCustomerById(customerName),HttpStatus.OK);
        }
        catch (CustomerNotFoundException customerNotFoundException)
        {
            throw customerNotFoundException;
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        System.out.print(customer.getEmailId());
        return new ResponseEntity<>(customerService.updateCustomer(customer),HttpStatus.OK);
    }

    @RequestMapping("/getdetails")
    public ResponseEntity<?> getdetails(HttpServletRequest httpServletRequest){
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println("hello this is the customer email");
        System.out.print("email " +email);
        return new ResponseEntity<>(customerService.getCustomerdetaild(email),HttpStatus.OK);
    }

    @PostMapping("/addproduct")
    public ResponseEntity<?> addProduct(@RequestBody Products products)
    {
            return new ResponseEntity<>(customerService.addProduct(products), HttpStatus.CREATED);

    }

    @PostMapping("/addproducttouser")
    public ResponseEntity<?> addProducttouser(@RequestBody Products products,HttpServletRequest httpServletRequest)
    {
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(products);
        return new ResponseEntity<>(customerService.addProducttocustomer(email,products), HttpStatus.CREATED);

    }
    @GetMapping("/getproductofuser")
    public ResponseEntity<?> getProductofuser(HttpServletRequest httpServletRequest) throws ProductNotFoundException {
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        return new ResponseEntity<>(customerService.getProductofcustomer(email), HttpStatus.CREATED);

    }
    @DeleteMapping("/deleteproduct/{productid}/{email}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productid,@PathVariable String email) throws ProductNotFoundException {
        return new ResponseEntity<>(customerService.deleteProductById(email,productid), HttpStatus.CREATED);
    }
}
