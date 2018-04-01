package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealStock;
import com.github.it89.investordaybook.model.daybook.TradeAccount;
import com.github.it89.investordaybook.repository.DealStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class DealStockServiceImpl implements DealStockService {
    private final DealStockRepository dealStockRepository;

    @Autowired
    public DealStockServiceImpl(DealStockRepository dealStockRepository) {
        this.dealStockRepository = dealStockRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DealStock findById(long id) {
        return dealStockRepository.findById(id).get();
    }

    @Override
    public void save(DealStock dealStock) {
        dealStockRepository.save(dealStock);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DealStock> getList(AppUser appUser) {
        return dealStockRepository.getAllByAppUser(appUser);
    }

    @Override
    public DealStock findByDealNumberAndAccount(String dealNumber, TradeAccount account) {
        return dealStockRepository.getByDealNumberAndAccount(dealNumber, account);
    }

    @Override
    public List<DealStock> getAllByAccount(TradeAccount account) {
        return dealStockRepository.getAllByAccountOrderByDateTime(account);
    }
}
