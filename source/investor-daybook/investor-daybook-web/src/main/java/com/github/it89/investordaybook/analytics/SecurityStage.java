package com.github.it89.investordaybook.analytics;

import com.github.it89.investordaybook.model.daybook.Security;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SecurityStage {
    private Security security;
    private int stage;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public SecurityStage(Security security, int stage, LocalDate dateFrom, LocalDate dateTo) {
        this.security = security;
        this.stage = stage;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public SecurityStage(Security security, int stage, LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.security = security;
        this.stage = stage;
        this.dateFrom = dateFrom.toLocalDate();
        this.dateTo = dateTo.toLocalDate();
    }

    @Override
    public String toString() {
        return "SecurityStage{" +
                "security=" + security.getTicker() +
                ", stage=" + stage +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
