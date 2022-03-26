package org.hcl.hackathon.transactions.repository;

import org.hcl.hackathon.transactions.entity.TransactionDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
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
        TransactionDetails details = new TransactionDetails();
        details.setOrderReferenceNumber(12345);
        entityManager.persist(details);
        Integer referenceNumber = repository.findByOrderReferenceNumber(12345).getOrderReferenceNumber();
        assertEquals(String.valueOf(12345), String.valueOf(referenceNumber));
    }

}