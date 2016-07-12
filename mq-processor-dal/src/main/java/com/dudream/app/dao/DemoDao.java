package com.dudream.app.dao;

import com.dudream.app.domain.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoDao {

    void addDemo(Demo demo);

    Demo getDemo(Integer id);

}
