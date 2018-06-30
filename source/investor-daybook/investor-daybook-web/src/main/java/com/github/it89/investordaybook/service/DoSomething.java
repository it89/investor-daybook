package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.analytics.SecurityStage;
import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.service.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service("doSomething")
public class DoSomething {
    @Autowired
    AppUserService appUserService;
    @Autowired
    SecurityService securityService;

    @Autowired
    DealBondService dealBondService;

    @Autowired
    TradeAccountService tradeAccountService;

    @Autowired
    SecurityStageService securityStageService;

    public String doIt() {
        AppUser appUser = appUserService.findByLogin("axel");
        TradeAccount account = tradeAccountService.findByCode("102965i", appUser);
        List<SecurityStage> result = securityStageService.findAllByTradeAccount(account);

        return "xxx";
    }
}
