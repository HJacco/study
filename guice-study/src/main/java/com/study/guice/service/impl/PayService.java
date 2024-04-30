package com.study.guice.service.impl;

import com.study.guice.annotation.Service;

@Service
public class PayService {

    public String pay(String billId) {
        return "success pay for bill : " + billId;
    }
}
