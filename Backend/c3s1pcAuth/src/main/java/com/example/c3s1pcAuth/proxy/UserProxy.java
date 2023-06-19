package com.example.c3s1pcAuth.proxy;


import com.example.c3s1pcAuth.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CustomerApp",url = "localhost:8809")
public interface UserProxy {
    @PostMapping("/api/v1/add")
    public ResponseEntity<?> sendDataToProductApp(@RequestBody UserDTO userDTO);
}
