package com.github.it89.investordaybook.model.daybook;

import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "deal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Deal {
    @Nullable
    protected Long id;
    protected int version;
    protected Security security;
    protected String dealNumber;
    protected LocalDateTime dateTime;
    /*protected TradeOperation operation;
    protected long amount;
    protected BigDecimal volume;
    protected BigDecimal commission;*/

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
    @Column(name = "version", nullable = false)
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
    @Column(name = "deal_number", nullable = false)
    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    @NotNull
    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /*public TradeOperation getOperation() {
        return operation;
    }

    public void setOperation(TradeOperation operation) {
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
        this.volume = volume;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }*/
}
