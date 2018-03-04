package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.service.dao.AppUserService;
import com.github.it89.investordaybook.service.dao.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("doSomething")
public class DoSomething {
    @Autowired
    AppUserService appUserService;
    @Autowired
    SecurityService securityService;

    public String doIt() {
        /*Security security = new Security();
        security.setCaption("cap");
        security.setIsin("4545454");
        security.setTicker("ttt");
        security.setAppUser(appUserService.findByLogin("test"));
        securityService.save(security);
        Security s = securityService.findById(security.getId());
        return s.toString();*/
        AppUser user = appUserService.findByLogin("test");
        System.out.println("--------user----------");
        return securityService.findByIsin("4545454", user).toString();
    }

    public static void main(String[] args) {
        System.out.println(SecurityType.STOCK);
    }
}
