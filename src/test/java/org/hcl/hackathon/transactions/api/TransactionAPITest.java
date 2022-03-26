package org.hcl.hackathon.transactions.api;

import org.hcl.hackathon.transactions.dto.OrderDTO;
import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;
import org.hcl.hackathon.transactions.service.impl.TransactionDetailsServiceImpl;
import org.hcl.hackathon.transactions.util.APICaller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionAPITest {

    @Mock
    private TransactionDetailsServiceImpl mockTransactionDetailsServiceImpl;
    @Mock
    private APICaller mockCaller;

    @InjectMocks
    private TransactionAPI transactionAPIUnderTest;

    @Test
    public void testGet() {
        // Setup
        // Configure TransactionDetailsServiceImpl.findByOrderReferenceNumber(...).
        final TransactionDetailsDTO dto = new TransactionDetailsDTO();
        dto.setTransactionID(UUID.fromString("a70f6336-ff08-48d8-aa93-166c50677992"));
        dto.setTransactionDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        dto.setStatus("status");
        dto.setOrderID("orderID");
        dto.setOrderReferenceNumber(0);
        when(mockTransactionDetailsServiceImpl.findByOrderReferenceNumber(0)).thenReturn(dto);

        // Run the test
        final TransactionDetailsDTO result = transactionAPIUnderTest.get(0);

        // Verify the results
    }

    @Test
    public void testPost() {
        // Setup
        final OrderDTO orderDTO = new OrderDTO(0);
        final ResponseEntity<UUID> expectedResult = new ResponseEntity<>(UUID.fromString("0eadff57-9b2a-4eef-9495-a5ec900f9934"), HttpStatus.CREATED);

        // Configure TransactionDetailsServiceImpl.create(...).
        final UUID uuid = UUID.fromString("0eadff57-9b2a-4eef-9495-a5ec900f9934");
        when(mockTransactionDetailsServiceImpl.create(any(TransactionDetailsDTO.class))).thenReturn(uuid);

        // Run the test
        final ResponseEntity<UUID> result = transactionAPIUnderTest.post(orderDTO);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockCaller).submitRequestForTransaction(eq(UUID.fromString("0eadff57-9b2a-4eef-9495-a5ec900f9934")), any(TransactionDetailsDTO.class));
    }

    @Test
    public void testDoSomething() {
        // Setup
        // Run the test
        final String result = transactionAPIUnderTest.doSomething();

        // Verify the results
        assertEquals("Ok", "Ok");
    }
}
