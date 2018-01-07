package com.github.it89.investordaybook;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("doSomething")
public class DoSomething {
    @Autowired
    private AppUserService appUserService;

    public String doIt() {
        AppUser appUser = appUserService.findByLogin("log");
        return appUser.toString();
    }
}
