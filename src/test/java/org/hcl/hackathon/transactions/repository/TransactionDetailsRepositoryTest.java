package org.hcl.hackathon.transactions.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionDetailsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionDetailsRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByOrderReferenceNumber() {
        assertEquals(null, repository);
    }

}