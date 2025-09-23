package com.example.banksystem.Accounts.ServiceImpl;

import com.example.banksystem.Accounts.Const.StatusEnum;
import com.example.banksystem.Accounts.DTO.CloseAccountDTO;
import com.example.banksystem.Accounts.DTO.UserAccountDTO;
import com.example.banksystem.Accounts.Entity.Accounts;
import com.example.banksystem.Accounts.Repository.AccountsRepository;
import com.example.banksystem.Accounts.Repository.UserAccountJDBC;
import com.example.banksystem.Accounts.Service.AccountService;
import com.example.banksystem.User.Entity.User;
import com.example.banksystem.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccountJDBC userAccountJDBC;

    //create user account
    @Override
    public Accounts saveAccountDetails(Accounts accounts, Authentication authentication) {
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        accounts.setUser(user);
        Accounts saveAccount = accountsRepository.save(accounts);
        return accountsRepository.save(saveAccount);

    }

    //Get all users account
    @Override
    public List<UserAccountDTO> getUserAccount(Authentication authentication) {
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userAccountJDBC.findByUserAccount();
    }

    //Get account details by id
    @Override
    public List<UserAccountDTO> getAccountById(int account_id, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userAccountJDBC.findByAccountId(account_id);
    }

    //add inactive account
    @Override
    public Accounts getInactiveAccountById(CloseAccountDTO closeAccountDTO, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        Accounts account = accountsRepository.findById(closeAccountDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Mark as INACTIVE
        account.setStatus(StatusEnum.INACTIVE);

        // Associate with user who performed the action (optional)
        account.setUser(user);
        return accountsRepository.save(account);
    }

}
