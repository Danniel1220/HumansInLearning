package com.crystal.dao;

public class Track implements Playable {
    private final String title;
    private final long length;

    public Track(String title, long length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public long getLength() {
        return length;
    }

    public String toString() {
        return "Title: " + this.title + " | Length: " + this.length;
    }

    public void play() {
        System.out.println("Track " + this.title + " plays!");
    }
}
