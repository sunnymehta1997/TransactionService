package org.hcl.hackathon.transactions.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionDetailsDTO {

    private UUID transactionID;

    private LocalDateTime transactionDate;

    private String status;//submitted, completed, failed

    private String orderID;

    private Integer orderReferenceNumber;

}
