package com.dudream.app.domain;

import com.dudream.app.domain.base.AbstractModel;

public class Demo extends AbstractModel {

    private Integer id;

    private String name;

    public Demo() {
    }

    public Demo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
