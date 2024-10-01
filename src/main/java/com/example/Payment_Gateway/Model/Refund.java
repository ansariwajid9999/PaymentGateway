package com.example.Payment_Gateway.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Ref;

@Entity
@Data
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer refundId;

    private Integer refundAmount;

    private Integer userId;

    private Integer transactionId;

    public Refund() {
    }
}
