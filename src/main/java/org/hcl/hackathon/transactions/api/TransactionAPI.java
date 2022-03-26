package org.hcl.hackathon.transactions.api;

import org.hcl.hackathon.transactions.constants.Status;
import org.hcl.hackathon.transactions.dto.OrderDTO;
import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;
import org.hcl.hackathon.transactions.exceptions.TransactionNotFoundException;
import org.hcl.hackathon.transactions.service.impl.TransactionDetailsServiceImpl;
import org.hcl.hackathon.transactions.util.APICaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionAPI {

    @Autowired
    private TransactionDetailsServiceImpl transactionDetailsServiceImpl;

    @Autowired
    private APICaller caller;

    /**
     *
     * @param id orderReferenceId
     * @return Transaction details
     */
    @GetMapping(path = "/{id}")
    public TransactionDetailsDTO get(@PathVariable Integer id) {
        TransactionDetailsDTO dto = transactionDetailsServiceImpl.findByOrderReferenceNumber(id);
        if(dto == null || !id.equals(dto.getOrderReferenceNumber())) {
            throw new TransactionNotFoundException(String.format("'%d' Not found", id));
        }
        return dto;
    }

    /**
     *
     * @param orderDTO order details for which transaction need to be carried out
     * @return UUID of the transaction
     */
    @PostMapping
    public ResponseEntity<UUID> post(@RequestBody OrderDTO orderDTO) {
        TransactionDetailsDTO dto = new TransactionDetailsDTO();
        dto.setOrderReferenceNumber(orderDTO.getOrderReferenceNumber());
        dto.setStatus(Status.SUBMITTED.name());
        dto.setTransactionDate(LocalDateTime.now());
        UUID uuid = transactionDetailsServiceImpl.create(dto);
        caller.submitRequestForTransaction(uuid, dto);
        return new ResponseEntity<>(uuid, HttpStatus.CREATED);
    }

    /**
     *
     * @return String to check if server is live or not
     */
    @GetMapping(path = "/heartBeat")
    public String doSomething() {
        return "Ok";
    }

}
