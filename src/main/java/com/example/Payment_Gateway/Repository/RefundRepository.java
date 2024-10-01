package com.example.Payment_Gateway.Repository;

import com.example.Payment_Gateway.Model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund , Integer> {
}
