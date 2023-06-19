package com.example.c3s1pcAuth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.LICENSE;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
   private String emailId;
   private String name ;
   private String address;
   private String phoneNo;
}
