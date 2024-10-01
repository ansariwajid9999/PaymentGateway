package com.example.Payment_Gateway.Service;

import com.example.Payment_Gateway.CustomException.*;
import com.example.Payment_Gateway.Enum.Status;
import com.example.Payment_Gateway.Model.Transaction;
import com.example.Payment_Gateway.Model.User;
import com.example.Payment_Gateway.Repository.TransactionRepository;
import com.example.Payment_Gateway.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public String addTransaction(Transaction transaction) throws Exception{
        if(transaction.getTransactionId() != null){
            throw new addTransactionException("transaction is already present in Database.");
        }

        transactionRepository.save(transaction);
        return "transaction has been successfully saved in Database.";
    }

    public String userTransaction(Integer userId, Integer transactionId) throws Exception{
        Optional<User> optional = userRepository.findById(userId);
        Optional<Transaction> optional1 = transactionRepository.findById(transactionId);

        if(optional.isEmpty()){
            throw new userTransactionException("user is not present in Database.");
        }
        if(optional1.isEmpty()){
            throw new TransactionUserException("transaction is not present in Database.");
        }

        User user = optional.get();
        Transaction transaction = optional1.get();

        transaction.setUser(user);
        user.getTransactionList().add(transaction);

        userRepository.save(user);
        transactionRepository.save(transaction);

        return "user and transaction has been successfully mapped.";
    }

    public Integer amountTransaction() throws Exception{
        List<Transaction> transactionList = transactionRepository.findTransactionByStatus(Status.SUCCESS);

        if(transactionList.isEmpty()){
            throw new amountTransactionException("no transaction of SUCCESS state present in Database.");
        }

        Integer amount = 0;
        for(Transaction transaction : transactionList){
            amount += transaction.getAmount();
        }

        return amount;
    }

    public Integer userAmountTransaction(Integer userId) throws Exception{
        Optional<User> optional = userRepository.findById(userId);

        if(optional.isEmpty()){
            throw new userTransactionAmountException("no transaction of SUCCESS state present in Database.");
        }

        User user = optional.get();
        List<Transaction> transactionList = user.getTransactionList();

        if(transactionList.isEmpty()){
            throw new Exception("transaction list is empty in Database.");
        }

        Integer amount = 0;

        for(Transaction transaction : transactionList){
            if(transaction.getStatus() == Status.SUCCESS){
                amount += transaction.getAmount();
            }
        }

        return amount;
    }
}
