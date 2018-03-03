package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.service.dao.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("doSomething")
public class DoSomething {
    @Autowired
    AppUserService appUserService;

    public String doIt() {
        return appUserService.findByLogin("Axel").toString();
    }

    public static void main(String[] args) {
        System.out.println(SecurityType.STOCK);
    }
}
