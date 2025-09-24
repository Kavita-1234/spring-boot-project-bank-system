package com.example.banksystem.Accounts.Service;

import com.example.banksystem.Accounts.DTO.CloseAccountDTO;
import com.example.banksystem.Accounts.DTO.UserAccountDTO;
import com.example.banksystem.Accounts.Entity.Accounts;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountService {

    //Create account
    Accounts saveAccountDetails(Accounts accounts, Authentication authentication);

    //Update account
    Accounts updateAccountDetails(int account_id, Accounts accounts, Authentication authentication);

    //Get all users account
    List<UserAccountDTO> getUserAccount(Authentication authentication);

    //Get account details by id
    List<UserAccountDTO> getAccountById(int account_id, Authentication authentication);

    //add inactive account
    Accounts getInactiveAccountById(CloseAccountDTO closeAccountDTO, Authentication authentication);

}
