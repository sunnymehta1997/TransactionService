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

    public List<TransactionDetailsDTO> findAll() {
        return transactionDetailsRepository.findAll()
                .stream()
                .map(transactiondetails -> mapToDTO(transactiondetails, new TransactionDetailsDTO()))
                .collect(Collectors.toList());
    }

    public TransactionDetailsDTO findByOrderReferenceNumber(Integer orderReferenceNumber) {
        return mapToDTO(transactionDetailsRepository.findByOrderReferenceNumber(orderReferenceNumber), new TransactionDetailsDTO());
    }

    public UUID create(final TransactionDetailsDTO transactiondetailsDTO) {
        final TransactionDetails transactiondetails = new TransactionDetails();
        mapToEntity(transactiondetailsDTO, transactiondetails);
        return transactionDetailsRepository.save(transactiondetails).getTransactionID();
    }

    public void update(final UUID transactionID,
                       final TransactionDetailsDTO transactiondetailsDTO) {
        final TransactionDetails transactiondetails = transactionDetailsRepository.findById(transactionID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(transactiondetailsDTO, transactiondetails);
        transactionDetailsRepository.save(transactiondetails);
    }

    public void delete(final UUID transactionID) {
        transactionDetailsRepository.deleteById(transactionID);
    }

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
