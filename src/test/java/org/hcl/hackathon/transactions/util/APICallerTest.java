package org.hcl.hackathon.transactions.util;

import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;
import org.hcl.hackathon.transactions.service.impl.TransactionDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class APICallerTest {

    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private TransactionDetailsServiceImpl mockService;

    @InjectMocks
    private APICaller apiCallerUnderTest;

    @Test
    public void testSubmitRequestForTransaction() {
        // Setup
        final TransactionDetailsDTO dto = new TransactionDetailsDTO();
        dto.setTransactionID(UUID.fromString("c33a6a84-53ff-45b6-8157-6da8194efd25"));
        dto.setTransactionDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        dto.setStatus("status");
        dto.setOrderID("orderID");
        dto.setOrderReferenceNumber(0);

        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockExecutorService).execute(any(Runnable.class));

        // Run the test
        apiCallerUnderTest.submitRequestForTransaction(UUID.fromString("c33a6a84-53ff-45b6-8157-6da8194efd25"), dto);

        // Verify the results
        verify(mockExecutorService).execute(any(Runnable.class));
        verify(mockService).update(eq(UUID.fromString("c33a6a84-53ff-45b6-8157-6da8194efd25")), any(TransactionDetailsDTO.class));
    }
}
