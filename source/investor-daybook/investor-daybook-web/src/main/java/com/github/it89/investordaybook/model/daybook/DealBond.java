package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DealBond extends Deal {
    private BigDecimal pricePct;
    private BigDecimal accumulatedCouponYield;

    public BigDecimal getPricePct() {
        return pricePct;
    }

    public void setPricePct(BigDecimal pricePct) {
        if (pricePct == null) {
            throw new IllegalArgumentException("pricePct must be specified");
        }
        this.pricePct = pricePct;
    }

    public BigDecimal getAccumulatedCouponYield() {
        return accumulatedCouponYield;
    }

    public void setAccumulatedCouponYield(BigDecimal accumulatedCouponYield) {
        if (accumulatedCouponYield == null) {
            throw new IllegalArgumentException("accumulatedCouponYield must be specified");
        }
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
                /*", dateTime=" + dateTime +
                ", operation=" + operation +
                ", amount=" + amount +
                ", volume=" + volume +
                ", commission=" + commission +*/
                '}';
    }
}
