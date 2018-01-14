package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Deal {
    @Nullable
    protected Long id;
    protected Security security;
    protected String dealNumber;
    protected LocalDateTime dateTime;
    protected TradeOperation operation;
    protected long amount;
    protected BigDecimal volume;
    protected BigDecimal commission;
    protected AppUser appUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        if (security == null) {
            throw new IllegalArgumentException("security must be specified");
        }
        this.security = security;
    }

    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        if ((dealNumber == null) || dealNumber.length() == 0) {
            throw new IllegalArgumentException("dealNumber must be specified");
        }
        this.dealNumber = dealNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("dateTime must be specified");
        }
        this.dateTime = dateTime;
    }

    public TradeOperation getOperation() {
        return operation;
    }

    public void setOperation(TradeOperation operation) {
        if (operation == null) {
            throw new IllegalArgumentException("operation must be specified");
        }
        this.operation = operation;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        if (volume == null) {
            throw new IllegalArgumentException("volume must be specified");
        }
        this.volume = volume;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        if (commission == null) {
            throw new IllegalArgumentException("commission must be specified");
        }
        this.commission = commission;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        if (appUser == null) {
            throw new IllegalArgumentException("appUser must be specified");
        }
        this.appUser = appUser;
    }
}
