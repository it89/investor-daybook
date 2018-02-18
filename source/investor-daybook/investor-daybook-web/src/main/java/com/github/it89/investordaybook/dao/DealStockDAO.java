package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealStock;

import java.util.List;

public interface DealStockDAO {
    DealStock findById(long id);
    Long findIdByDealNumber(String dealNumber, long idAppUser);
    void save(DealStock deal);
    List<DealStock> getList(AppUser appUser);
}
