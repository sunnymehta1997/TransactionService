package org.hcl.hackathon.transactions.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
public class TransactionDetails {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID transactionID;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column
    private String status;

    @Column
    private String orderID;

    private Integer orderReferenceNumber;

}
