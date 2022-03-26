package org.hcl.hackathon.transactions.util;

import org.hcl.hackathon.transactions.constants.Status;
import org.hcl.hackathon.transactions.dto.TransactionDetailsDTO;
import org.hcl.hackathon.transactions.service.impl.TransactionDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Service
public class APICaller {

    private static final Logger LOGGER = LoggerFactory.getLogger(APICaller.class.getName());

    @Autowired
    private ExecutorService executorService;

    private RestTemplate restTemplate;

    @Autowired
    private TransactionDetailsServiceImpl service;

    public APICaller() {
        restTemplate = new RestTemplate();
    }

    public void submitRequestForTransaction(UUID uuid, TransactionDetailsDTO dto) {
        executorService.execute(() -> {
            String status;
            try {
                Thread.sleep(5 * 1000L);//lazy service call
                int n = new Random().nextInt(100);
                if(n <= 50) {
                    status = Status.COMPLETED.name();
                } else {
                    status = Status.FAILED.name();
                }
            } catch (InterruptedException ex) {
                LOGGER.error("Exception in calling external service");
                status = Status.FAILED.name();
                dto.setStatus(status);
                service.update(uuid, dto);
                informCaller(dto.getOrderReferenceNumber(), status);
                Thread.currentThread().interrupt();
            }
        });
    }

    private void informCaller(Integer orderReferenceNumber, String status) {
        LOGGER.info("Push kafka event for order number {} with status {}", orderReferenceNumber, status);
        String queueName = "transaction_update";
        String message = orderReferenceNumber + "|" + status;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:9101/activemq-service/produce?queueName=" + queueName + "&message=" + message, String.class);
        if(!responseEntity.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("Failed to transmit the status {}", responseEntity.getStatusCodeValue());
            LOGGER.error("Response message is {}", responseEntity.getBody());
            return;
        }
        String responseBody = responseEntity.getBody();
        LOGGER.info("Message transferred successfully for order number {} with status {} : {}", orderReferenceNumber, status, responseBody);
    }

}
