package com.studentregistration.studentregistration.repository;

import com.studentregistration.studentregistration.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetRepository extends JpaRepository<PaymentDetails,Long> {
}
