package com.bluetooth.imooc_music.models;

import io.realm.RealmObject;

public class MusicModel extends RealmObject {
    private String musicId;
    private String name;
    private String poster;
    private String path;
    private String author;

    public MusicModel() {
    }

    public MusicModel(String musicId, String name, String poster, String path, String author) {
        this.musicId = musicId;
        this.name = name;
        this.poster = poster;
        this.path = path;
        this.author = author;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
