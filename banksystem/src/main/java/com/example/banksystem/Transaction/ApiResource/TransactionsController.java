package com.example.banksystem.Transaction.ApiResource;


import com.example.banksystem.Transaction.DTO.*;
import com.example.banksystem.Transaction.Entity.Transactions;
import com.example.banksystem.Transaction.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/save-txn")
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    //Save transaction
    @PostMapping("/details")
    public ResponseEntity<Map<String, String>> saveTransactionDetails(@RequestBody TransSaveDTO transSaveDTO, Authentication authentication){
        Transactions saveTransaction = transactionService.saveTransactionDetails(transSaveDTO, authentication);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "New Transaction created successfully");
        return ResponseEntity.ok(response);
    }

    //add transaction withdraw amount
    @PostMapping("/trans-withdraw-amount")
    public ResponseEntity<Map<String, String>> getByTransactionWithdrawAmount(@RequestBody WithdrawReqDTO withdrawReqDTO, Authentication authentication){
        Transactions result = transactionService.getByTransactionWithdrawAmount(withdrawReqDTO, authentication);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Withdraw money transaction");
        return ResponseEntity.ok(response);
    }

    //add transaction deposit amount
    @PostMapping("/trans-deposit-amount")
    public ResponseEntity<Map<String, String>> getByTransDepositAmount(@RequestBody DepositReqDTO depositReqDTO, Authentication authentication){
        Transactions result = transactionService.getByTransactionDepositAmount(depositReqDTO, authentication);
        HashMap<String , String> response = new HashMap<>();
        response.put("message", "Deposit money transaction");
        return ResponseEntity.ok(response);
    }

    //Get transaction history by accountId
    @GetMapping("/trans-history")
    public ResponseEntity<List<TransHistoryByIdDTO>> getByTransactionHistory(@RequestParam("account_id") int account_id, Authentication authentication){
        List<TransHistoryByIdDTO> result = transactionService.getByTransactionHistoryByAccountId(account_id, authentication);
        return  ResponseEntity.ok(result);
    }

    //Get all transaction history by admin
    @GetMapping("/trans-admin-history")
    public ResponseEntity<List<TransHistoryByAdminDTO>> getByTransactionHistoryByAdmin(@RequestParam String role, Authentication authentication){
        List<TransHistoryByAdminDTO> result = transactionService.getByTransactionHistoryByAdmin(role, authentication);
        return ResponseEntity.ok(result);
    }

    //Add Transaction one account to other account
    @PostMapping("/trans-amount-acc")
    public ResponseEntity<Map<String, String>> getByTransactionAmountTransfer(@RequestBody TransAmountReqDTO transAmountReqDTO, Authentication authentication){
        Transactions result = transactionService.getByTransactionAmountTransfer(transAmountReqDTO, authentication);
        HashMap<String , String> response = new HashMap<>();
        response.put("message", "Deposit money transaction");
        return ResponseEntity.ok(response);
    }
}
