package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DealStock extends Deal {
    private BigDecimal price;

    public static class Builder {
        private Long id;
        private Security security;
        private String dealNumber;
        private LocalDateTime dateTime;
        private TradeOperation operation;
        private long amount;
        private BigDecimal volume;
        private BigDecimal commission;
        private AppUser appUser;
        private BigDecimal price;

        public Builder(String dealNumber) {
            this.dealNumber = dealNumber;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder security(Security security) {
            this.security = security;
            return this;
        }

        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder operation(TradeOperation operation) {
            this.operation = operation;
            return this;
        }

        public Builder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Builder volume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public Builder commission(BigDecimal commission) {
            this.commission = commission;
            return this;
        }

        public Builder appUser(AppUser appUser) {
            this.appUser = appUser;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        DealStock build() {
            DealStock deal = new DealStock();
            deal.setId(this.id);
            deal.setSecurity(this.security);
            deal.setDealNumber(this.dealNumber);
            deal.setDateTime(this.dateTime);
            deal.setOperation(this.operation);
            deal.setAmount(this.amount);
            deal.setVolume(this.volume);
            deal.setCommission(this.commission);
            deal.setAppUser(this.appUser);
            deal.setPrice(this.price);
            return deal;
        }
    }

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
