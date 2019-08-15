package com.bluetooth.rxandroidtest;

public class CateModel {
    private int imageId;
    private int avatarId;
    private String name;
    private String nickname;

    public CateModel() {
    }

    public CateModel(String name, String nickname,int imageId, int avatarId) {
        this.imageId = imageId;
        this.avatarId = avatarId;
        this.name = name;
        this.nickname = nickname;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
