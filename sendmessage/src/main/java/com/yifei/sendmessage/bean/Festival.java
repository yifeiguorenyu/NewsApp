package com.yifei.sendmessage.bean;

import java.util.Date;

public class Festival {
    private int id;
    private String name;
    private String desc;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Festival() { }

    public Festival(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", date=" + date +
                '}';
    }
}
