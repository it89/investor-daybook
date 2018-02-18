package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealBond;

import java.util.List;

public interface DealBondDAO {
    DealBond findById(long id);
    Long findIdByDealNumber(String dealNumber, long idAppUser);
    void save(DealBond deal);
    List<DealBond> getList(AppUser appUser);
}
