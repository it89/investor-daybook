package com.github.it89.investordaybook.model.daybook;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DealBondTest {
    @Test
    void getCashFlow() {
        DealBond deal = new DealBond();

        deal.setOperation(TradeOperation.BUY);
        deal.setVolume(new BigDecimal("1024.23"));
        deal.setCommission(new BigDecimal("0.39"));
        deal.setAccumulatedCouponYield(new BigDecimal("67.12"));
        assertEquals(new BigDecimal("-1091.74"), deal.getCashFlow());
        deal.setOperation(TradeOperation.SELL);
        assertEquals(new BigDecimal("1090.96"), deal.getCashFlow());
    }

}