package com.example.banksystem.Accounts.ApiResource;

import com.example.banksystem.Accounts.DTO.CloseAccountDTO;
import com.example.banksystem.Accounts.DTO.UserAccountDTO;
import com.example.banksystem.Accounts.Entity.Accounts;
import com.example.banksystem.Accounts.Service.AccountService;
import com.example.banksystem.Transaction.DTO.WithdrawReqDTO;
import com.example.banksystem.Transaction.Entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/save-account")
public class AccountsController {

    @Autowired
    private AccountService accountService;

    //Create new account
    @PostMapping("/details")
    public ResponseEntity<Map<String, String>> saveAccountDetails(@RequestBody Accounts accounts, Authentication authentication){
        Accounts saveAccount = accountService.saveAccountDetails(accounts, authentication);
        HashMap<String, String> response = new HashMap<>();
        response.put("message","User account created successfully");
        return ResponseEntity.ok(response);
    }

    //Get all users account
    @GetMapping("/get-user-account")
    public ResponseEntity<List<UserAccountDTO>> getUserAccount(Authentication authentication){
       List<UserAccountDTO> result = accountService.getUserAccount(authentication);
       return ResponseEntity.ok(result);
    }

    //Get account by id
    @GetMapping("/get-account-id")
    public ResponseEntity<List<UserAccountDTO>> getAccountById(@RequestParam int account_id, Authentication authentication){
        List<UserAccountDTO> result = accountService.getAccountById(account_id, authentication);
        return ResponseEntity.ok(result);
    }

    //Add inactive account by id
    @PostMapping("/add-inactive-account-id")
    public ResponseEntity<Map<String, String>> getInactiveAccountById(@RequestBody CloseAccountDTO closeAccountDTO, Authentication authentication){
        Accounts result = accountService.getInactiveAccountById(closeAccountDTO, authentication);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Inactive account");
        return ResponseEntity.ok(response);
    }

}
