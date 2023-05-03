package com.EFMS.Entity2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Payment {

    private Long id;
    private Long OrderId;
    private double cardNumber;
    private double expDate;
    private String name;
    private String status;

    public double getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(double cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getExpDate() {
        return expDate;
    }

    public void setExpDate(double expDate) {
        this.expDate = expDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

