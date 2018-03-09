package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.daybook.DealStock;
import com.github.it89.investordaybook.repository.DealStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
