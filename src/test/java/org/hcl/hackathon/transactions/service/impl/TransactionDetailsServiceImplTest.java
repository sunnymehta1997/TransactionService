package org.hcl.hackathon.transactions.service.impl;

import org.hcl.hackathon.transactions.constants.Status;
import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;
import org.hcl.hackathon.transactions.entity.TransactionDetails;
import org.hcl.hackathon.transactions.repository.TransactionDetailsRepository;
import org.hcl.hackathon.transactions.service.impl.TransactionDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.NoTransactionException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TransactionDetailsServiceImplTest {

    @Mock
    private TransactionDetailsRepository repository;

    @InjectMocks
    private TransactionDetailsServiceImpl transactionDetailsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAll() {
        List<TransactionDetailsDTO> transactionDetails = transactionDetailsService.findAll();
        assertNotNull(transactionDetails);
        assertEquals(0, transactionDetails.size());
    }

    @Test
    public void findByOrderReferenceNumberNotFound() {
        Integer orderReferenceNumber = 12345;
        TransactionDetailsDTO dto = transactionDetailsService.findByOrderReferenceNumber(orderReferenceNumber);
        assertNull(dto);
    }

    @Test
    public void findByOrderReferenceNumberFound() throws Exception {
        Integer orderReferenceNumber = 12345;
        TransactionDetails dto = new TransactionDetails();
        dto.setOrderReferenceNumber(orderReferenceNumber);
        dto.setStatus(Status.COMPLETED.name());
        Mockito.when(repository.findByOrderReferenceNumber(orderReferenceNumber)).thenReturn(dto);
        TransactionDetailsDTO dbDto = transactionDetailsService.findByOrderReferenceNumber(orderReferenceNumber);
        assertNotNull(dto);
        assertEquals(dbDto.getOrderReferenceNumber(), orderReferenceNumber);
        assertEquals(dbDto.getStatus(), Status.COMPLETED.name());
    }

    @Test
    public void createTransaction() {
        Integer orderReferenceNumber = 12345;
        TransactionDetailsDTO dto = new TransactionDetailsDTO();
        dto.setOrderReferenceNumber(orderReferenceNumber);
        dto.setStatus(Status.SUBMITTED.name());
        dto.setTransactionDate(LocalDateTime.now());
        TransactionDetails details = new TransactionDetails();
        details.setOrderReferenceNumber(orderReferenceNumber);
        details.setTransactionDate(dto.getTransactionDate());
        details.setStatus(dto.getStatus());
        UUID expected = UUID.randomUUID();
        details.setTransactionID(expected);
        Mockito.when(repository.save(Mockito.any(TransactionDetails.class))).thenReturn(details);
        UUID uuid = transactionDetailsService.create(dto);
        assertNotNull(uuid);
        assertEquals(expected, uuid);
        assertEquals(orderReferenceNumber, dto.getOrderReferenceNumber());
    }

}