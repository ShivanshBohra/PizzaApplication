package com.example.c3s1pcAuth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpData {
    private String name;
    private String emailId;
    private String password;
    private String address;
    private String phoneNo;
}
