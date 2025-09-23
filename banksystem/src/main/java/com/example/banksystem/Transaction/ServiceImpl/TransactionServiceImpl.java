package com.example.banksystem.Transaction.ServiceImpl;

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
        transaction.setUser(user);
        transaction.setAccounts(account);
        transaction.setAmount(transSaveDTO.getAmount());
        transaction.setType(transSaveDTO.getType());
        transaction.setTxnDate(transSaveDTO.getTxnDate());
        transaction.setRelatedAccountId(receiverAccount);

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

        //validation
        if(withdrawReqDTO.getAmount() <=0){
            throw new InvalidAmountException("Invalid amount");
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

        //validation
        if(depositReqDTO.getAmount() <=0){
            throw new InvalidAmountException("Invalid amount");
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
    public List<TransHistoryByAdminDTO> getByTransactionHistoryByAdmin(String role, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return transactionJDBC.findByTransHistoryByAdmin(role);
    }

    //Add Transaction one account to other account
    public Transactions getByTransactionAmountTransfer(TransAmountReqDTO transAmountReqDTO, Authentication authentication){
        String userName = authentication.getName();
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Accounts senderAcc = accountsRepository.findById(transAmountReqDTO.getSenderAccountId())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Accounts recAcc = accountsRepository.findById(transAmountReqDTO.getReceiverAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        //validation
        if(transAmountReqDTO.getAmount() <=0){
            throw new InvalidAmountException("Invalid amount");
        }

        // Update balances
        senderAcc.setBalance(senderAcc.getBalance() - transAmountReqDTO.getAmount());
        accountsRepository.save(senderAcc);

        recAcc.setBalance(recAcc.getBalance() + transAmountReqDTO.getAmount()); // ADD to receiver
        accountsRepository.save(recAcc);

        Date now = new Date();

        // Save transactions
        Transactions debit = new Transactions();
        debit.setRelatedAccountId(senderAcc);
        debit.setType(TransactionEnum.TRANSFER_OUT);
        debit.setAmount(transAmountReqDTO.getAmount());
        debit.setTxnDate(now);
        transactionsRepository.save(debit);

        Transactions credit = new Transactions();
        credit.setRelatedAccountId(recAcc);
        credit.setType(TransactionEnum.TRANSFER_IN);
        credit.setAmount(transAmountReqDTO.getAmount());
        credit.setTxnDate(now);
        return transactionsRepository.save(credit);

}

}
