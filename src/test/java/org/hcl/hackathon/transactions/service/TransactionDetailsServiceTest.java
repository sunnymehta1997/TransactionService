package org.hcl.hackathon.transactions.service;

import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class TransactionDetailsServiceTest {

    @Autowired
    private TransactionDetailsService transactionDetailsService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAll() {
        List<TransactionDetailsDTO> transactionDetails = transactionDetailsService.findAll();
        assertNotNull(transactionDetails);
        assertNotEquals(0, transactionDetails.size());
    }
}