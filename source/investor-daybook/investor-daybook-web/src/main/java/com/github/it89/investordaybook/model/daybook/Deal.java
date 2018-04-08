package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.analytics.CashFlow;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "deal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Deal implements CashFlow {
    @Nullable
    protected Long id;
    protected int version;
    protected Security security;
    protected String dealNumber;
    protected LocalDateTime dateTime;
    protected TradeOperation operation;
    protected long amount;
    protected BigDecimal volume;
    protected BigDecimal commission;
    @Nullable
    protected Integer stage;
    TradeAccount account;

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "security_id")
    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    @NotNull
    @Column(name = "deal_number")
    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    @NotNull
    @Column(name = "date_time")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @NotNull
    @Column(name = "operation")
    @Enumerated(EnumType.STRING)
    public TradeOperation getOperation() {
        return operation;
    }

    public void setOperation(TradeOperation operation) {
        this.operation = operation;
    }

    @NotNull
    @Column(name = "amount")
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @NotNull
    @Column(name = "volume")
    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    @NotNull
    @Column(name = "commission")
    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    @Nullable
    @Column(name = "stage")
    public Integer getStage() {
        return stage;
    }

    public void setStage(@Nullable Integer stage) {
        this.stage = stage;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "trade_account_id")
    public TradeAccount getAccount() {
        return account;
    }

    public void setAccount(TradeAccount account) {
        this.account = account;
    }

    @Override
    @Transient
    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public static long getAmountSum(List<? extends Deal> deals) {
        long amountSum = 0;
        for (Deal deal : deals) {
            if (deal.getOperation() == TradeOperation.BUY) {
                amountSum += deal.getAmount();
            } else {
                amountSum -= deal.getAmount();
            }
        }
        return amountSum;
    }
}
