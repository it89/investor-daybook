package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.dao.DealStockDAO;
import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dealStockService")
@Transactional
public class DealStockServiceImpl implements DealStockService {
    @Autowired
    private DealStockDAO dao;

    @Override
    public DealStock findById(long id) {
        return dao.findById(id);
    }

    @Override
    public Long findIdByDealNumber(String dealNumber, long idAppUser) {
        return dao.findIdByDealNumber(dealNumber, idAppUser);
    }

    @Override
    public void save(DealStock dealStock) {
        dao.save(dealStock);
    }

    @Override
    public List<DealStock> getList(AppUser appUser) {
        return dao.getList(appUser);
    }
}
