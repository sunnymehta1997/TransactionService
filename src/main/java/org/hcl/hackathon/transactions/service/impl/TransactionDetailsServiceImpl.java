package org.hcl.hackathon.transactions.service.impl;

import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;
import org.hcl.hackathon.transactions.entity.TransactionDetails;
import org.hcl.hackathon.transactions.repository.TransactionDetailsRepository;
import org.hcl.hackathon.transactions.service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    /**
     *
     * @return All the transactions from database
     */
    public List<TransactionDetailsDTO> findAll() {
        return transactionDetailsRepository.findAll()
                .stream()
                .map(transactiondetails -> mapToDTO(transactiondetails, new TransactionDetailsDTO()))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param orderReferenceNumber order reference number to search
     * @return Transaction details of the given order reference
     */
    public TransactionDetailsDTO findByOrderReferenceNumber(Integer orderReferenceNumber) {
        return mapToDTO(transactionDetailsRepository.findByOrderReferenceNumber(orderReferenceNumber), new TransactionDetailsDTO());
    }

    /**
     * Create new transaction
     * @param transactiondetailsDTO transaction details
     * @return UUID of the transaction
     */
    public UUID create(final TransactionDetailsDTO transactiondetailsDTO) {
        final TransactionDetails transactiondetails = new TransactionDetails();
        mapToEntity(transactiondetailsDTO, transactiondetails);
        return transactionDetailsRepository.save(transactiondetails).getTransactionID();
    }

    /**
     * Update transaction status
     * @param transactionID UUID of the transaction
     * @param transactiondetailsDTO transaction details
     */
    public void update(final UUID transactionID,
                       final TransactionDetailsDTO transactiondetailsDTO) {
        final TransactionDetails transactiondetails = transactionDetailsRepository.findById(transactionID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(transactiondetailsDTO, transactiondetails);
        transactionDetailsRepository.save(transactiondetails);
    }

    /**
     * Convert entity into dto
     * @param transactiondetails to be copied into
     * @param transactiondetailsDTO to be copied from
     * @return
     */
    private TransactionDetailsDTO mapToDTO(final TransactionDetails transactiondetails,
                                           final TransactionDetailsDTO transactiondetailsDTO) {
        if(transactiondetails == null) {
            return null;
        }
        transactiondetailsDTO.setTransactionID(transactiondetails.getTransactionID());
        transactiondetailsDTO.setTransactionDate(transactiondetails.getTransactionDate());
        transactiondetailsDTO.setStatus(transactiondetails.getStatus());
        transactiondetailsDTO.setOrderID(transactiondetails.getOrderID());
        transactiondetailsDTO.setOrderReferenceNumber(transactiondetails.getOrderReferenceNumber());
        return transactiondetailsDTO;
    }

    /**
     * Convert DTO into entity
     * @param transactiondetailsDTO to be copied from
     * @param transactiondetails to be copied into
     * @return
     */
    private TransactionDetails mapToEntity(final TransactionDetailsDTO transactiondetailsDTO,
                                           final TransactionDetails transactiondetails) {
        if(transactiondetailsDTO == null) {
            return null;
        }
        transactiondetails.setTransactionDate(transactiondetailsDTO.getTransactionDate());
        transactiondetails.setStatus(transactiondetailsDTO.getStatus());
        transactiondetails.setOrderID(transactiondetailsDTO.getOrderID());
        transactiondetails.setOrderReferenceNumber(transactiondetailsDTO.getOrderReferenceNumber());
        return transactiondetails;
    }

}
