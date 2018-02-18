package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.dao.DealBondDAO;
import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealBond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dealBondService")
@Transactional
public class DealBondServiceImpl implements DealBondService {
    @Autowired
    private DealBondDAO dao;

    @Override
    public DealBond findById(long id) {
        return dao.findById(id);
    }

    @Override
    public Long findIdByDealNumber(String dealNumber, long idAppUser) {
        return dao.findIdByDealNumber(dealNumber, idAppUser);
    }

    @Override
    public void save(DealBond dealBond) {
        dao.save(dealBond);
    }

    @Override
    public List<DealBond> getList(AppUser appUser) {
        return dao.getList(appUser);
    }
}
