package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.TradeAccount;

public interface TradeAccountService {
    TradeAccount findById(long id);
    TradeAccount findByCode(String code, AppUser appUser);
    void save(TradeAccount tradeAccount);
}
