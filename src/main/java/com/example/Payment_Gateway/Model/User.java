package com.example.Payment_Gateway.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    private String emailId;

    private String accountNo;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Transaction> transactionList;
}
