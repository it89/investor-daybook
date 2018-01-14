package com.github.it89.investordaybook;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.SecurityBond;
import com.github.it89.investordaybook.model.daybook.SecurityStock;
import com.github.it89.investordaybook.model.daybook.SecurityType;
import com.github.it89.investordaybook.service.AppUserService;
import com.github.it89.investordaybook.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("doSomething")
public class DoSomething {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SecurityService securityService;

    public String doIt() {
        AppUser appUser = appUserService.findByLogin("log");

        SecurityStock securityStock = new SecurityStock.Builder("222").ticker("GZPR").caption("Газпром")
                .codeGRN("333").appUser(appUser).build();

        securityService.save(securityStock);

        SecurityBond securityBond = new SecurityBond.Builder("xxx").ticker("ofz").caption("ОФЗ")
                .codeGRN("xxe").appUser(appUser).build();

        securityService.save(securityBond);

        return securityBond.toString();
    }

    public static void main(String[] args) {
        System.out.println(SecurityType.STOCK);
    }
}
