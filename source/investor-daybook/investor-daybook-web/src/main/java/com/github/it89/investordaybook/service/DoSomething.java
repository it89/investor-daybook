package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.daybook.*;
import org.springframework.stereotype.Service;

@Service("doSomething")
public class DoSomething {
    public String doIt() {
        return "1";
    }

    public static void main(String[] args) {
        System.out.println(SecurityType.STOCK);
    }
}
