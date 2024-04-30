package com.study.guice.service.impl;

import com.study.guice.annotation.Service;
import com.study.guice.service.BillService;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class BillServiceImpl implements BillService {

    private AtomicLong count = new AtomicLong();

    @Override
    public String bill() {
        return String.valueOf(count.addAndGet(1L));
    }
}
