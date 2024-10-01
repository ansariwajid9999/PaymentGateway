package com.example.Payment_Gateway.Controller;

import com.example.Payment_Gateway.Model.Transaction;
import com.example.Payment_Gateway.Service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/transaction")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addTransaction")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction){
        try {
            String result = transactionService.addTransaction(transaction);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Unable to add Transaction in Database.");
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/userTransaction")
    public ResponseEntity<String> userTransaction(@RequestParam("userId") Integer userId , @RequestParam("transactionId") Integer transactionId){
        try {
            String result = transactionService.userTransaction(userId , transactionId);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Unable to map user to transaction.");
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/amountTransaction")
    public ResponseEntity<Integer> amountTransaction(){
        try {
            Integer result = transactionService.amountTransaction();
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("All transactions are FAILURE.");
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userAmountTransaction")
    public ResponseEntity<Integer> userAmountTransaction(@RequestParam("userId") Integer userId){
        try {
            Integer result = transactionService.userAmountTransaction(userId);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("All transactions are FAILURE for given userId.");
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }
}
