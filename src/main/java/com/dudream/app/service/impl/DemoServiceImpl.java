package com.dudream.app.service.impl;

import com.dudream.app.dao.DemoDao;
import com.dudream.app.domain.Demo;
import com.dudream.app.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dudream on 2016/7/10.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Override
    public void addDemo(Demo demo) {
        demoDao.addDemo(demo);
    }

    @Override
    public Demo getDemo(Integer id) {
        return demoDao.getDemo(id);
    }
}
