package com.github.it89.investordaybook;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.service.AppUserService;
import com.github.it89.investordaybook.service.DealBondService;
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
    @Autowired
    private DealBondService dealBondService;

    public String doIt() {
        AppUser appUser = appUserService.findByLogin("log");

        SecurityStock securityStock = new SecurityStock.Builder("222").ticker("GZPR").caption("Газпром")
                .codeGRN("333").appUser(appUser).build();

        securityService.save(securityStock);

        SecurityBond securityBond = new SecurityBond.Builder("xxx").ticker("ofz").caption("ОФЗ")
                .codeGRN("xxe").appUser(appUser).build();

        securityService.save(securityBond);

        DealStock deal = new DealStock.Builder("777")
                .security(securityStock)
                .dateTime(LocalDateTime.now())
                .operation(TradeOperation.BUY)
                .amount(5)
                .volume(BigDecimal.valueOf(6262.1))
                .commission(BigDecimal.valueOf(0.52))
                .appUser(appUser)
                .price(BigDecimal.valueOf(1252.42))
                .build();

        dealStockService.save(deal);

        DealBond dealBond = new DealBond.Builder("778")
                .security(securityBond)
                .dateTime(LocalDateTime.now())
                .operation(TradeOperation.BUY)
                .amount(5)
                .volume(BigDecimal.valueOf(6262.1))
                .commission(BigDecimal.valueOf(0.52))
                .appUser(appUser)
                .pricePct(BigDecimal.valueOf(99.5453))
                .accumulatedCouponYield(BigDecimal.valueOf(15.58))
                .build();

        dealBondService.save(dealBond);

        return dealBond.toString();
        /*Long id = dealStockService.findIdByDealNumber("777", appUser.getId());
        if (id != null)
            return id.toString();
        return "no!!!";*/
    }

    public static void main(String[] args) {
        System.out.println(SecurityType.STOCK);
    }
}
