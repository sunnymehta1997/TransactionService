package org.hcl.hackathon.transactions.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/activemq-service")
public class ActiveMQController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMQController.class.getName());

    @GetMapping(path = "/produce")
    public String produce(@RequestParam String queueName, @RequestParam String message) {
        LOGGER.info("Event captured queye : {} message : {}", queueName, message);
        return "success";
    }

}
