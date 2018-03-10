package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.service.dao.AppUserService;
import com.github.it89.investordaybook.service.dao.DealBondService;
import com.github.it89.investordaybook.service.dao.DealStockService;
import com.github.it89.investordaybook.service.dao.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service("doSomething")
public class DoSomething {
    @Autowired
    AppUserService appUserService;
    @Autowired
    SecurityService securityService;

    @Autowired
    DealBondService dealBondService;

    public String doIt() {
        AppUser appUser = appUserService.findByLogin("test");
        Security security = securityService.findByIsin("4545454x", appUser);
        if (security == null) {
            security = new Security();
            security.setIsin("4545454x");
            security.setAppUser(appUser);
        }
        security.setCaption("cap");
        security.setTicker("ttt");
        securityService.save(security);

        DealBond dealStock = new DealBond();
        dealStock.setSecurity(security);
        dealStock.setDealNumber("777");
        dealStock.setPricePct(BigDecimal.valueOf(100.459545766556));
        dealStock.setAccumulatedCouponYield(BigDecimal.valueOf(457.14));
        dealStock.setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        dealStock.setOperation(TradeOperation.BUY);
        dealStock.setAmount(5);
        dealStock.setVolume(BigDecimal.valueOf(50.34));
        dealStock.setCommission(BigDecimal.valueOf(0.54));
        dealBondService.save(dealStock);
        return dealBondService.findById(dealStock.getId()).toString();
    }
}
