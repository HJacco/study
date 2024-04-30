package com.study.guice.service.impl;

import com.google.inject.Inject;
import com.study.guice.annotation.Service;
import com.study.guice.service.BillService;
import com.study.guice.service.PurchaseService;

@Service
public class PurchaseServiceIMpl implements PurchaseService {
    @Inject
    private BillService billService;
    @Inject
    private PayService payService;

    @Override
    public String process(Long billId) {
        System.out.println(payService.pay(billId + ""));
        return billService.bill();
    }
}
