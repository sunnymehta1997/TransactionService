package org.hcl.hackathon.transactions.repository;

import java.util.UUID;

import org.hcl.hackathon.transactions.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, UUID> {

    TransactionDetails findByOrderReferenceNumber(Integer orderReferenceNumber);

}
