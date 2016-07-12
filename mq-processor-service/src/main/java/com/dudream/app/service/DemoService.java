package com.dudream.app.service;

import com.dudream.app.domain.Demo;

/**
 * Created by dudream on 2016/7/10.
 */
public interface DemoService {

    void addDemo(Demo demo);

    Demo getDemo(Integer id);

}
