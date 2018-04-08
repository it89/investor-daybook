package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.analytics.SecurityStage;
import com.github.it89.investordaybook.model.daybook.TradeAccount;

import java.util.List;

public interface SecurityStageService {
    List<SecurityStage> findAllByTradeAccount(TradeAccount account);
}
