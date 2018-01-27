package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.daybook.DealStock;

public interface DealStockDAO {
    DealStock findById(long id);
    Long findIdByDealNumber(String dealNumber, long idAppUser);
    void save(DealStock deal);
}
