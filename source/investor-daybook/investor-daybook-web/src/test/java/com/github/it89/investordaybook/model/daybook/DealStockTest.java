package com.github.it89.investordaybook.model.daybook;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealStockTest {
    @Test
    void getCashFlow() {
        DealStock deal = new DealStock();

        deal.setOperation(TradeOperation.BUY);
        deal.setVolume(new BigDecimal("2967.5"));
        deal.setCommission(new BigDecimal("1.39"));
        assertEquals(new BigDecimal("-2968.89"), deal.getCashFlow());
        deal.setOperation(TradeOperation.SELL);
        assertEquals(new BigDecimal("2966.11"), deal.getCashFlow());
    }


}