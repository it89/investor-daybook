package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.daybook.DealBond;

public interface DealBondService {
    DealBond findById(long id);
    void save(DealBond dealStock);
}
