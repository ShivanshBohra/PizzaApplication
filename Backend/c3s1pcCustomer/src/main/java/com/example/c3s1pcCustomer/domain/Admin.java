package com.example.c3s1pcCustomer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
    private String emailId;
    private String password;
    private String role;
}
