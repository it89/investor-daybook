package com.github.it89.investordaybook.analytics;

import com.github.it89.investordaybook.model.daybook.Security;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SecurityStage {
    private Security security;
    private int stage;
    private LocalDate dateFrom;
    @Nullable
    private LocalDate dateTo;
    //TODO rename amountSum to amount?
    private long amountSum;
    private List<CashFlow> cashFlow = new ArrayList<>();
    private List<CashFlow> cashFlowFeature = new ArrayList<>();

    public SecurityStage(Security security, int stage, LocalDate dateFrom, @Nullable LocalDate dateTo) {
        this.security = security;
        this.stage = stage;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public SecurityStage(Security security, int stage, LocalDateTime dateFrom, LocalDateTime dateTo) {
        this(security, stage, dateFrom.toLocalDate(), dateTo.toLocalDate());
    }

    public Security getSecurity() {
        return security;
    }

    public int getStage() {
        return stage;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public long getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(long amountSum) {
        this.amountSum = amountSum;
        if (amountSum != 0) {
            dateTo = null;
        }
    }

    public List<CashFlow> getCashFlow() {
        return cashFlow;
    }

    public List<CashFlow> getCashFlowFeature() {
        return cashFlowFeature;
    }

    @Override
    public String toString() {
        return "SecurityStage{" +
                "security=" + security.getTicker() +
                ", stage=" + stage +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", amountSum=" + amountSum +
                '}';
    }
}
