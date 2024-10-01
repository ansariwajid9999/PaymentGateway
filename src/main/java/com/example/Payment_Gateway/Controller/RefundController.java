package com.example.Payment_Gateway.Controller;

import com.example.Payment_Gateway.Service.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/refund")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @PostMapping("/addRefund")
    public ResponseEntity<String> addRefund(@RequestParam("transactionId") Integer transactionId){
        try {
            String result = refundService.addRefund(transactionId);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Unable to add refund in Database.");
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userWithMaxRefund")
    public ResponseEntity<Integer> userWithMaxRefund(){
        try {
            Integer result = refundService.userWithMaxRefund();
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Unable to fetch the maximum refund from the Database.");
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }
}
