package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealBond;
import com.github.it89.investordaybook.repository.DealBondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class DealBondServiceImpl implements DealBondService {
    private final DealBondRepository dealBondRepository;

    @Autowired
    public DealBondServiceImpl(DealBondRepository dealBondRepository) {
        this.dealBondRepository = dealBondRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DealBond findById(long id) {
        return dealBondRepository.findById(id).get();
    }

    @Override
    public void save(DealBond dealStock) {
        dealBondRepository.save(dealStock);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DealBond> getList(AppUser appUser) {
        return dealBondRepository.getAllByAppUser(appUser);
    }
}
