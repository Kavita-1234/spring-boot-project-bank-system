package com.example.banksystem.Transaction.Repository;

import com.example.banksystem.Transaction.Entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

}
