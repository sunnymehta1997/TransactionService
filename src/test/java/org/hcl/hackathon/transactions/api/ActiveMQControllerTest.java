package org.hcl.hackathon.transactions.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ActiveMQControllerTest {

    private ActiveMQController activeMQControllerUnderTest;

    @Before
    public void setUp() {
        activeMQControllerUnderTest = new ActiveMQController();
    }

    @Test
    public void testProduce() {
        // Setup
        // Run the test
        final String result = activeMQControllerUnderTest.produce("queueName", "message");

        // Verify the results
        assertEquals("success", result);
    }
}
