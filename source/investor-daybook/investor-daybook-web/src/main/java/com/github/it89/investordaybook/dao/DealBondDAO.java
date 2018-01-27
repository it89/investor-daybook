package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.daybook.DealBond;

public interface DealBondDAO {
    DealBond findById(long id);
    Long findIdByDealNumber(String dealNumber, long idAppUser);
    void save(DealBond deal);
}
