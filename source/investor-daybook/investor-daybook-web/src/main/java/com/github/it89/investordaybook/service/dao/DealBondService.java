package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealBond;
import com.github.it89.investordaybook.model.daybook.TradeAccount;

import java.util.List;

public interface DealBondService {
    DealBond findById(long id);
    void save(DealBond dealStock);
    List<DealBond> getList(AppUser appUser);
    DealBond findByDealNumberAndAccount(String dealNumber, TradeAccount account);
    List<DealBond> getAllByAccount(TradeAccount account);
}
