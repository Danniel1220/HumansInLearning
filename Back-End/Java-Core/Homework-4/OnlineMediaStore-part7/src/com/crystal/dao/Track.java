package com.crystal.dao;

public class Track implements Playable, Comparable<Track> {
    private final String title;
    private final float length;

    public Track(String title, long length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public float getLength() {
        return length;
    }

    public String toString() {
        return "Title: " + this.title + " | Length: " + this.length;
    }

    public void play() {
        System.out.println("Track " + this.title + " plays!");
    }

    @Override
    public int compareTo(Track t) {
        // Compare by title but if title is the same then by length.
        //return this.title.compareTo(t.title) == 0 ? Float.compare(this.length, t.length) : this.title.compareTo(t.title);

        // Compare by length, but if length is the same then by title.
        return Float.compare(this.length, t.length) == 0 ? this.title.compareTo(t.title) : Float.compare(this.length, t.length);
    }
}
