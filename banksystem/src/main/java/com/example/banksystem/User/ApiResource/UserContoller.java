package com.example.banksystem.User.ApiResource;

import com.example.banksystem.User.DTO.UserAdminDTO;
import com.example.banksystem.User.Entity.User;
import com.example.banksystem.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/save-user")
public class UserContoller {

    @Autowired
    private UserService userService;

    //Register new user
    @PostMapping("/user-details")
    public ResponseEntity<Map<String, String>> saveUserDetails(@RequestBody User userEntity){
        User savedUser = userService.saveUserDetails(userEntity);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User Registered successfully");
        return ResponseEntity.ok(response);
    }

    //list of admin user
    @GetMapping("/user-admin")
    public ResponseEntity<List<UserAdminDTO>> getByAdminUser(@RequestParam String role, Authentication authentication){
        List<UserAdminDTO> result = userService.getByAdminUser(role, authentication);
        return ResponseEntity.ok(result);
    }

}
