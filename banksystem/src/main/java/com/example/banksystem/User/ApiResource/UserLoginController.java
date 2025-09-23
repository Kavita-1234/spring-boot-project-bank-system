package com.example.banksystem.User.ApiResource;

import com.example.banksystem.User.Entity.User;
import com.example.banksystem.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    private UserService userService;

    //User role login
    @GetMapping("/user")
    public Map<String, String> userLogin() {
        Map<String, String> response = new HashMap<>();
        response.put("message","User login successfully");
        return response;
    }

    //Admin role login
    @GetMapping("/admin")
    public Map<String, String> adminPage() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Admin login successfully");
        return response;
    }
}

