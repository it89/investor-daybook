package com.github.it89.investordaybook.analytics;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CashFlow {
    LocalDate getDate();
    BigDecimal getCashFlow();
}
