package com.example.banksystem.Accounts.ServiceImpl;

import com.example.banksystem.Accounts.Const.StatusEnum;
import com.example.banksystem.Accounts.DTO.CloseAccountDTO;
import com.example.banksystem.Accounts.DTO.UserAccountDTO;
import com.example.banksystem.Accounts.Entity.Accounts;
import com.example.banksystem.Accounts.Repository.AccountsRepository;
import com.example.banksystem.Accounts.Repository.UserAccountJDBC;
import com.example.banksystem.Accounts.Service.AccountService;
import com.example.banksystem.GlobalExceptionHandler.InvalidAmountException;
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

        //validation
        if(accounts.getBalance() <= 0){
            throw new InvalidAmountException("Invalid amount. Please enter an amount greater than 0.");
        }
        accounts.setUser(user);
        Accounts saveAccount = accountsRepository.save(accounts);
        return accountsRepository.save(saveAccount);
    }

    //Update account by id
    @Override
    public Accounts updateAccountDetails(int account_id, Accounts accounts, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        //validation amount
        if(accounts.getBalance() <=0 ){
            throw new InvalidAmountException("Invalid amount");
        }

        return accountsRepository.findById(account_id)
                .map(existingAccount -> {
                    existingAccount.setAccountNumber(accounts.getAccountNumber());
                    existingAccount.setAccount_type(accounts.getAccount_type());
                    existingAccount.setStatus(accounts.getStatus());
                    existingAccount.setBalance(accounts.getBalance());
                    existingAccount.setOpenedDate(accounts.getOpenedDate());
                    existingAccount.setUser(user);
                    return accountsRepository.save(existingAccount);
                })
                .orElseThrow(() -> new RuntimeException("Account not found by:"+ account_id));
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

        // user performed the action
        account.setUser(user);
        return accountsRepository.save(account);
    }

}
