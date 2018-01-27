package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.daybook.DealBond;

public interface DealBondService {
    DealBond findById(long id);
    Long findIdByDealNumber(String dealNumber, long idAppUser);
    void save(DealBond dealBond);
}
