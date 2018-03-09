package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.daybook.DealStock;

public interface DealStockService {
    DealStock findById(long id);
    void save(DealStock dealStock);
}
