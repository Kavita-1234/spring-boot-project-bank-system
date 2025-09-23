package com.example.banksystem.User.ServiceImpl;

import com.example.banksystem.User.DTO.AdminTransactionDTO;
import com.example.banksystem.User.DTO.UserAdminDTO;
import com.example.banksystem.User.Entity.User;
import com.example.banksystem.User.Repository.UserAdminJDBC;
import com.example.banksystem.User.Repository.UserRepository;
import com.example.banksystem.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAdminJDBC userAdminJDBC;

    //New User Register
    @Override
    public User saveUserDetails(User userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    //load user details from the database by username and map then into spring security UserDetails object
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                //Assign role to user (USER, ADMIN)
                .roles(user.getRole().name().replace("ROLE_", ""))  // USER or ADMIN
                .build();
    }

    //Get all admin user
    @Override
    public List<UserAdminDTO> getByAdminUser(String role, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("Admin not found"));
        return userAdminJDBC.findByAdminUser(role);
    }

}
