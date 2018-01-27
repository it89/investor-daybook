package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DealBond extends Deal {
    private BigDecimal pricePct;
    private BigDecimal accumulatedCouponYield;

    public static class Builder {
        private Long id;
        private SecurityBond security;
        private String dealNumber;
        private LocalDateTime dateTime;
        private TradeOperation operation;
        private long amount;
        private BigDecimal volume;
        private BigDecimal commission;
        private AppUser appUser;
        private BigDecimal pricePct;
        private BigDecimal accumulatedCouponYield;

        public Builder(String dealNumber) {
            this.dealNumber = dealNumber;
        }

        public DealBond.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public DealBond.Builder security(SecurityBond security) {
            this.security = security;
            return this;
        }

        public DealBond.Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public DealBond.Builder operation(TradeOperation operation) {
            this.operation = operation;
            return this;
        }

        public DealBond.Builder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public DealBond.Builder volume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public DealBond.Builder commission(BigDecimal commission) {
            this.commission = commission;
            return this;
        }

        public DealBond.Builder appUser(AppUser appUser) {
            this.appUser = appUser;
            return this;
        }

        public DealBond.Builder pricePct(BigDecimal pricePct) {
            this.pricePct = pricePct;
            return this;
        }

        public DealBond.Builder accumulatedCouponYield(BigDecimal accumulatedCouponYield) {
            this.accumulatedCouponYield = accumulatedCouponYield;
            return this;
        }

        public DealBond build() {
            DealBond deal = new DealBond();
            deal.setId(this.id);
            deal.setSecurity(this.security);
            deal.setDealNumber(this.dealNumber);
            deal.setDateTime(this.dateTime);
            deal.setOperation(this.operation);
            deal.setAmount(this.amount);
            deal.setVolume(this.volume);
            deal.setCommission(this.commission);
            deal.setAppUser(this.appUser);
            deal.setPricePct(this.pricePct);
            deal.setAccumulatedCouponYield(this.accumulatedCouponYield);
            return deal;
        }
    }

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
                ", dateTime=" + dateTime +
                ", operation=" + operation +
                ", amount=" + amount +
                ", volume=" + volume +
                ", commission=" + commission +
                ", appUser=" + appUser +
                '}';
    }
}
