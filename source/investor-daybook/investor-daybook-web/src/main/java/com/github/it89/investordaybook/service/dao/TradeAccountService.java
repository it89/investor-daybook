package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import com.github.it89.investordaybook.model.daybook.TradeAccount;

import java.util.List;

public interface TradeAccountService {
    TradeAccount findById(long id);
    TradeAccount findByCode(String code, AppUser appUser);
    void save(TradeAccount tradeAccount);
}
