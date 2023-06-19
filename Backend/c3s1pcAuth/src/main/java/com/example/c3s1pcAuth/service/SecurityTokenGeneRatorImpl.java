package com.example.c3s1pcAuth.service;

import com.example.c3s1pcAuth.domain.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneRatorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(Customer customer) {
        // generate JWT user algo, userdata, issued time, expiration time, secret key...
        Map<String,String> result = new HashMap<String,String>();
        customer.setPassword("");

        Map<String,Object> userdata = new HashMap<>();

        userdata.put("user_",customer.getRole());
        userdata.put("user_email",customer.getEmailId());
        String jwt = Jwts.builder()
                .setClaims(userdata) // emailid,  role
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "mysecretkey")
                .compact();

        result.put("token",jwt);
        result.put("message","User login success");
        result.put("role",customer.getRole());
        result.put("email",customer.getEmailId());
        result.put("password",customer.getPassword());
        return result;
    }
}
