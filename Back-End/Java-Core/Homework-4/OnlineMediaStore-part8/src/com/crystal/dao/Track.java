package com.crystal.dao;

import com.crystal.exceptions.PlayerException;

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

    public void play() throws PlayerException {
        if (length > 0) {
            System.out.println("Track " + this.title + " plays!");
        }
        else {
            System.out.printf("Could not play track %s.\n", this.title);
            throw new PlayerException();
        }
    }

    @Override
    public int compareTo(Track t) {
        // Compare by title but if title is the same then by length.
        //return this.title.compareTo(t.title) == 0 ? Float.compare(this.length, t.length) : this.title.compareTo(t.title);

        // Compare by length, but if length is the same then by title.
        return Float.compare(this.length, t.length) == 0 ? this.title.compareTo(t.title) : Float.compare(this.length, t.length);
    }
}
