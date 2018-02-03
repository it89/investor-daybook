package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.daybook.DealStock;

public interface DealStockService {
    DealStock findById(long id);
    Long findIdByDealNumber(String dealNumber, long idAppUser);
    void save(DealStock dealStock);
}
