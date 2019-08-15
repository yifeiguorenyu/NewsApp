package com.yifei.sendmessage.bean;

public class Msg {
    private int id;
    private int festivalId;
    private String content;

    public Msg() {
    }

    public Msg(int id, int festivalId, String content) {
        this.id = id;
        this.festivalId = festivalId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFestivalId() {
        return festivalId;
    }

    public void setFestivalId(int festivalId) {
        this.festivalId = festivalId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", festivalId=" + festivalId +
                ", content='" + content + '\'' +
                '}';
    }
}
