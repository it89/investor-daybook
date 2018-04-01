package com.github.it89.investordaybook.model.daybook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "deal_stock")
public class DealStock extends Deal {
    private BigDecimal price;

    @NotNull
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    @Transient
    public BigDecimal getCashFlow() {
        BigDecimal cashFlow = commission.negate();
        if (operation == TradeOperation.BUY) {
            cashFlow = cashFlow.subtract(volume);
        } else {
            cashFlow = cashFlow.add(volume);
        }
        return cashFlow;
    }

    @Override
    public String toString() {
        return "DealStock{" +
                "price=" + price +
                ", id=" + id +
                ", version=" + version +
                ", security=" + security +
                ", dealNumber='" + dealNumber + '\'' +
                ", dateTime=" + dateTime +
                ", operation=" + operation +
                ", amount=" + amount +
                ", volume=" + volume +
                ", commission=" + commission +
                ", stage=" + stage +
                '}';
    }
}
