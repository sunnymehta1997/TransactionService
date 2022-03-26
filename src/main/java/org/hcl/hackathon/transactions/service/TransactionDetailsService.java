package org.hcl.hackathon.transactions.service;

import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface TransactionDetailsService {

    List<TransactionDetailsDTO> findAll();

    TransactionDetailsDTO findByOrderReferenceNumber(Integer orderReferenceNumber);

    TransactionDetailsDTO get(final UUID transactionID);

    UUID create(final TransactionDetailsDTO transactiondetailsDTO);

    void update(final UUID transactionID,
                final TransactionDetailsDTO transactiondetailsDTO);

}
