package com.github.it89.investordaybook;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.service.AppUserService;
import com.github.it89.investordaybook.service.DealStockService;
import com.github.it89.investordaybook.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service("doSomething")
public class DoSomething {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private DealStockService dealStockService;

    public String doIt() {
        AppUser appUser = appUserService.findByLogin("log");

        SecurityStock securityStock = new SecurityStock.Builder("222").ticker("GZPR").caption("Газпром")
                .codeGRN("333").appUser(appUser).build();

        securityService.save(securityStock);

        SecurityBond securityBond = new SecurityBond.Builder("xxx").ticker("ofz").caption("ОФЗ")
                .codeGRN("xxe").appUser(appUser).build();

        securityService.save(securityBond);

        DealStock deal = new DealStock.Builder("777")
                .security(securityBond)
                .dateTime(LocalDateTime.now())
                .operation(TradeOperation.BUY)
                .amount(5)
                .volume(BigDecimal.valueOf(6262.1))
                .commission(BigDecimal.valueOf(0.52))
                .appUser(appUser)
                .price(BigDecimal.valueOf(1252.42))
                .build();

        dealStockService.save(deal);

        //return deal.toString();
        Long id = dealStockService.findIdByDealNumber("777", appUser.getId());
        if (id != null)
            return id.toString();
        return "no!!!";
    }

    public static void main(String[] args) {
        System.out.println(SecurityType.STOCK);
    }
}
