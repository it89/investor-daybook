package com.github.it89.investordaybook.model.daybook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    public BigDecimal getCashFlow() {
        BigDecimal cashFlow = commission.negate();
        if (operation == TradeOperation.BUY) {
            cashFlow = cashFlow.subtract(volume).subtract(accumulatedCouponYield);
        } else {
            cashFlow = cashFlow.add(volume).add(accumulatedCouponYield);
        }
        return cashFlow;
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
                ", stage=" + stage +
                '}';
    }
}
