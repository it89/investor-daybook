package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import com.github.it89.investordaybook.model.daybook.TradeAccount;
import com.github.it89.investordaybook.repository.TradeAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class TradeAccountServiceImpl implements TradeAccountService {
    private final TradeAccountRepository tradeAccountRepository;

    @Autowired
    public TradeAccountServiceImpl(TradeAccountRepository tradeAccountRepository) {
        this.tradeAccountRepository = tradeAccountRepository;
    }

    @Override
    public TradeAccount findById(long id) {
        return tradeAccountRepository.findById(id).get();
    }

    @Override
    public TradeAccount findByCode(String code, AppUser appUser) {
        return tradeAccountRepository.findByCodeAndAppUser(code, appUser);
    }

    @Override
    public void save(TradeAccount tradeAccount) {
        tradeAccountRepository.save(tradeAccount);
    }
}
