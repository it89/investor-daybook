package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealStock;

import java.util.List;

public interface DealStockService {
    DealStock findById(long id);
    void save(DealStock dealStock);
    List<DealStock> getList(AppUser appUser);
}
