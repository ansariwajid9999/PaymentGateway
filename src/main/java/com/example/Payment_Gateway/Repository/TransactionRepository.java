package com.example.Payment_Gateway.Repository;

import com.example.Payment_Gateway.Enum.Status;
import com.example.Payment_Gateway.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction , Integer> {
    List<Transaction> findTransactionByStatus(Status status);
}
