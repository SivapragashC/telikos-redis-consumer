package com.example.telikosredisconsumer.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@ToString
@Table
@Builder
public class Booking implements Serializable {
    @Column("bookingid")
    @Id
    private String bookingId;
    @Column("logisticid")
    private String logisticId;
    @Column("capturedata")
    private String captureData;
    @Column("bookingstatus")
    private String bookingStatus;
    @Column("validationmessage")
    private String validationMessage;
    @Column("isfulfilment")
    private Boolean isFulfilment;
}
