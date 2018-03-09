package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.service.dao.AppUserService;
import com.github.it89.investordaybook.service.dao.DealStockService;
import com.github.it89.investordaybook.service.dao.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("doSomething")
public class DoSomething {
    @Autowired
    AppUserService appUserService;
    @Autowired
    SecurityService securityService;

    @Autowired
    DealStockService dealStockService;

    public String doIt() {
        AppUser appUser = appUserService.findByLogin("test");
        Security security = securityService.findByIsin("4545454", appUser);
        if (security == null) {
            security = new Security();
            security.setIsin("4545454");
            security.setAppUser(appUser);
        }
        security.setCaption("cap");
        security.setTicker("ttt");
        securityService.save(security);

        DealStock dealStock = new DealStock();
        dealStock.setSecurity(security);
        dealStock.setDealNumber("777");
        dealStock.setPrice(BigDecimal.valueOf(100.459));
        dealStockService.save(dealStock);
        return dealStockService.findById(dealStock.getId()).toString();
    }

    public static void main(String[] args) {
        System.out.println(SecurityType.STOCK);
    }
}
