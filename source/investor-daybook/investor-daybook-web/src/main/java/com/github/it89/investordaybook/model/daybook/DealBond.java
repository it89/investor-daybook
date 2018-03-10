package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "deal_bond")
public class DealBond extends Deal {
    private BigDecimal pricePct;
    private BigDecimal accumulatedCouponYield;

    @NotNull
    @Column(name = "price_pct")
    public BigDecimal getPricePct() {
        return pricePct;
    }

    public void setPricePct(BigDecimal pricePct) {
        this.pricePct = pricePct;
    }

    @NotNull
    @Column(name = "accumulated_coupon_yield")
    public BigDecimal getAccumulatedCouponYield() {
        return accumulatedCouponYield;
    }

    public void setAccumulatedCouponYield(BigDecimal accumulatedCouponYield) {
        this.accumulatedCouponYield = accumulatedCouponYield;
    }

    @Override
    public String toString() {
        return "DealBond{" +
                "pricePct=" + pricePct +
                ", accumulatedCouponYield=" + accumulatedCouponYield +
                ", id=" + id +
                ", security=" + security +
                ", dealNumber='" + dealNumber + '\'' +
                ", dateTime=" + dateTime +
                ", operation=" + operation +
                ", amount=" + amount +
                ", volume=" + volume +
                ", commission=" + commission +
                '}';
    }
}
