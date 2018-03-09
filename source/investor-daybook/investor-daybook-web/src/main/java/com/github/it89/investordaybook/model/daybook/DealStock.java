package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "deal_stock")
public class DealStock extends Deal {
    private BigDecimal price;

    @NotNull
    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("price must be specified");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "DealStock{" +
                "price=" + price +
                ", id=" + id +
                ", version=" + version +
                ", security=" + security +
                ", dealNumber='" + dealNumber + '\'' +
                /*", dateTime=" + dateTime +
                ", operation=" + operation +
                ", amount=" + amount +
                ", volume=" + volume +
                ", commission=" + commission +*/
                '}';
    }
}
