package com.project.bookmanagemementapi.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderBook implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderBookId;

    private Boolean isOpen = true;
    private int instrumentId;
    private String userId;


    @OneToMany(cascade = {CascadeType.ALL})
    private List<OrderRecord> orderRecords;

    public List<OrderExecution> getOrderExecutions() {
        return orderExecutions;
    }

    public void setOrderExecutions(List<OrderExecution> orderExecutions) {
        this.orderExecutions = orderExecutions;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    private List<OrderExecution> orderExecutions;

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getUserId() {
        return userId;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderRecord> getOrderRecords() {
        return orderRecords;
    }

    public void setOrderRecords(List<OrderRecord> orderRecords) {
        this.orderRecords = orderRecords;
    }
}
