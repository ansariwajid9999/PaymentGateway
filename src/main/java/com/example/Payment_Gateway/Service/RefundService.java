package com.example.Payment_Gateway.Service;

import com.example.Payment_Gateway.CustomException.amountTransactionException;
import com.example.Payment_Gateway.Enum.Status;
import com.example.Payment_Gateway.Model.Refund;
import com.example.Payment_Gateway.Model.Transaction;
import com.example.Payment_Gateway.Repository.RefundRepository;
import com.example.Payment_Gateway.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RefundService {

    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Integer userWithMaxRefund() throws Exception{
        List<Transaction> transactionList = transactionRepository.findTransactionByStatus(Status.FAILURE);

        if(transactionList.isEmpty()){
            throw new amountTransactionException("no transaction of FAILURE state present in Database.");
        }

        Integer maxAmount = 0;
        Integer maxRefundUserId = null;

        HashMap<Integer,Integer> map = new HashMap<>();
        for(Transaction transaction : transactionList){

            Integer Id = transaction.getUser().getUserId();
            Integer amount = transaction.getAmount();

            map.put(Id , map.getOrDefault(Id , 0) + amount);

            if(map.get(Id) > maxAmount){
                maxAmount = map.get(Id);
                maxRefundUserId = Id;
            }
        }
        return maxRefundUserId;
    }

    public String addRefund(Integer transactionId) throws Exception{
        Optional<Transaction> optional = transactionRepository.findById(transactionId);

        if(optional.isEmpty()){
            throw new Exception("No transaction is present with given Id.");
        }

        Transaction transaction = optional.get();
        if(transaction.getStatus() != Status.FAILURE){
            throw new Exception("Refund can only be created for failed transactions.");
        }

        Refund refund = new Refund();
        refund.setRefundAmount(transaction.getAmount());
        refund.setUserId(transaction.getUser().getUserId());
        refund.setTransactionId(transaction.getTransactionId());

        refundRepository.save(refund);

        return "Refund has been successfully saved in Database.";
    }
}
