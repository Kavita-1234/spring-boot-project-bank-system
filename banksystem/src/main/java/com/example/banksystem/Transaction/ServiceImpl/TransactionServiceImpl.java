package com.example.banksystem.Transaction.ServiceImpl;

import com.example.banksystem.Accounts.Const.StatusEnum;
import com.example.banksystem.Accounts.Entity.Accounts;
import com.example.banksystem.Accounts.Repository.AccountsRepository;
import com.example.banksystem.GlobalExceptionHandler.InvalidAmountException;
import com.example.banksystem.Transaction.Const.TransactionEnum;
import com.example.banksystem.Transaction.DTO.*;
import com.example.banksystem.Transaction.Entity.Transactions;
import com.example.banksystem.Transaction.Repository.TransactionJDBC;
import com.example.banksystem.Transaction.Repository.TransactionsRepository;
import com.example.banksystem.Transaction.Service.TransactionService;
import com.example.banksystem.User.Entity.User;
import com.example.banksystem.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionJDBC transactionJDBC;

    @Autowired
    private AccountsRepository accountsRepository;

    //Save transaction
    @Override
    public Transactions saveTransactionDetails(TransSaveDTO transSaveDTO, Authentication authentication) {
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        Accounts account = accountsRepository.findById(transSaveDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Accounts receiverAccount = null;
        if (transSaveDTO.getReceiverAccountId() != null) {
            receiverAccount = accountsRepository.findById(transSaveDTO.getReceiverAccountId())
                    .orElseThrow(() -> new RuntimeException("Receiver account not found"));
        }

        Transactions transaction = new Transactions();
        transaction.setAccounts(account);
        transaction.setAmount(transSaveDTO.getAmount());
        transaction.setType(transSaveDTO.getType());
        transaction.setTxnDate(transSaveDTO.getTxnDate());
        transaction.setRelatedAccountId(receiverAccount);
        transaction.setUser(user);

        return transactionsRepository.save(transaction);
    }

    //add withdraw amount
    @Override
    public Transactions getByTransactionWithdrawAmount(WithdrawReqDTO withdrawReqDTO, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Accounts account = accountsRepository.findById(withdrawReqDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        //validation for amount
        if(withdrawReqDTO.getAmount() <=0){
            throw new InvalidAmountException("Invalid amount");
        }

        //validation for account active and inactive
        if(account.getStatus() == StatusEnum.INACTIVE){
            throw new InvalidAmountException("Account inactive please activate your account");
        }

        //validation for insufficient balance
        if(account.getBalance() < withdrawReqDTO.getAmount()){
            throw new InvalidAmountException("Insufficient balance in sender account");
        }

        // Deduct balance
        account.setBalance(account.getBalance() - withdrawReqDTO.getAmount());
        accountsRepository.save(account);

        // Create transaction
        Transactions transaction = new Transactions();
        transaction.setAmount(withdrawReqDTO.getAmount());
        transaction.setType(TransactionEnum.WITHDRAWAL); // enforce type
        transaction.setAccounts(account);
        transaction.setTxnDate(new Date()); // auto-generated
        transaction.setUser(user);

        return transactionsRepository.save(transaction);

    }

    //add deposit amount
    @Override
    public Transactions getByTransactionDepositAmount(DepositReqDTO depositReqDTO, Authentication authentication) {
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Accounts account = accountsRepository.findById(depositReqDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        //validation for amount
        if(depositReqDTO.getAmount() <=0){
            throw new InvalidAmountException("Invalid amount");
        }

        //validation for active and inactivate account
        if(account.getStatus() == StatusEnum.INACTIVE) {
            throw new InvalidAmountException("Account inactive please activate your account");
        }

        //validation for insufficient amount
        if (account.getBalance() < depositReqDTO.getAmount()) {
            throw new InvalidAmountException("Insufficient balance in sender account");
        }

        // Update balances
        account.setBalance(account.getBalance() + depositReqDTO.getAmount());
        accountsRepository.save(account);

        //Create transaction
        Transactions transaction = new Transactions();
        transaction.setAmount(depositReqDTO.getAmount());
        transaction.setType(TransactionEnum.DEPOSIT);

        transaction.setAccounts(account);
        transaction.setTxnDate(new Date());
        transaction.setUser(user);

        return transactionsRepository.save(transaction);
    }


    //Get transaction history
    @Override
    public List<TransHistoryByIdDTO> getByTransactionHistoryByAccountId(int account_id, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return  transactionJDBC.findByTransHistoryByAccountId(account_id);
    }

    //Get all transaction history by admin
    @Override
    public List<TransHistoryByAdminDTO> getByTransactionHistoryByAdmin(String role, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return transactionJDBC.findByTransHistoryByAdmin(role);
    }

    //Add Transaction one account to other account
    @Override
    @Transactional
    public Transactions getByTransactionAmountTransfer(TransAmountReqDTO transAmountReqDTO, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Accounts senderAcc = accountsRepository.findById(transAmountReqDTO.getSenderAccountId())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Accounts recAcc = accountsRepository.findById(transAmountReqDTO.getReceiverAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        //validation for amount
        if(transAmountReqDTO.getAmount() <=0){
            throw new InvalidAmountException("Invalid amount");
        }

        //validation for sender account status
        if(senderAcc.getStatus() == StatusEnum.INACTIVE){
            throw new InvalidAmountException("Sender account inactivate");
        }

        //validation for receiver account status
        if(recAcc.getStatus() == StatusEnum.INACTIVE){
            throw new InvalidAmountException("Receiver account inactivate");
        }

        //validation for Insufficient amount
        if (senderAcc.getBalance() < transAmountReqDTO.getAmount()) {
            throw new InvalidAmountException("Insufficient balance in sender account");
        }

        // Update balances
        senderAcc.setBalance(senderAcc.getBalance() - transAmountReqDTO.getAmount());
        accountsRepository.save(senderAcc);

        recAcc.setBalance(recAcc.getBalance() + transAmountReqDTO.getAmount()); // ADD to receiver
        accountsRepository.save(recAcc);

        Date now = new Date();

        // Save transactions
        Transactions debit = new Transactions();

        debit.setAccounts(senderAcc);
        debit.setRelatedAccountId(recAcc);
        debit.setType(TransactionEnum.TRANSFER_OUT);
        debit.setAmount(transAmountReqDTO.getAmount());
        debit.setTxnDate(now);
        debit.setUser(user);
        transactionsRepository.save(debit);

        Transactions credit = new Transactions();

        credit.setAccounts(recAcc);
        credit.setRelatedAccountId(senderAcc);
        credit.setType(TransactionEnum.TRANSFER_IN);
        credit.setAmount(transAmountReqDTO.getAmount());
        credit.setTxnDate(now);
        credit.setUser(user);
        return transactionsRepository.save(credit);
    }

}
