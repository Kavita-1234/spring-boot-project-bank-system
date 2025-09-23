package com.example.banksystem.User.Service;

import com.example.banksystem.User.DTO.AdminTransactionDTO;
import com.example.banksystem.User.DTO.UserAdminDTO;
import com.example.banksystem.User.Entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    User saveUserDetails(User userEntity);

    //user login
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

    //Get admin user
    List<UserAdminDTO> getByAdminUser(String role, Authentication authentication);

}

