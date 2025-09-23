package com.example.banksystem.Transaction.Service;

import com.example.banksystem.Transaction.DTO.*;
import com.example.banksystem.Transaction.Entity.Transactions;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TransactionService {

    //Save transaction
    Transactions saveTransactionDetails(TransSaveDTO transSaveDTO, Authentication authentication);

    //add transaction withdraw amount
    Transactions getByTransactionWithdrawAmount(WithdrawReqDTO withdrawReqDTO, Authentication authentication);

    //Get transaction deposit amount
    Transactions getByTransactionDepositAmount(DepositReqDTO depositReqDTO, Authentication authentication);

    //Get transaction history
    List<TransHistoryByIdDTO> getByTransactionHistoryByAccountId(int account_id, Authentication authentication);

    //Get all transaction history by admin
    List<TransHistoryByAdminDTO> getByTransactionHistoryByAdmin(String role, Authentication authentication);

    //Add Transaction one account to other account
    Transactions getByTransactionAmountTransfer(TransAmountReqDTO transAmountReqDTO, Authentication authentication);

}
