package org.hcl.hackathon.transactions.util;

import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class APICallerTest {

    @Mock
    private ExecutorService executorService;

    @InjectMocks
    private APICaller caller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void submitRequestForTransaction() {
        UUID uuid = UUID.randomUUID();
        TransactionDetailsDTO dto = new TransactionDetailsDTO();
        caller.submitRequestForTransaction(uuid, dto);
        assertNotNull(caller);
    }

}