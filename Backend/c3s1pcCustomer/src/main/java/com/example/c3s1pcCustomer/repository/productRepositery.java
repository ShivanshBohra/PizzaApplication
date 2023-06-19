package com.example.c3s1pcCustomer.repository;


import com.example.c3s1pcCustomer.domain.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepositery extends MongoRepository<Products,String> {

}
