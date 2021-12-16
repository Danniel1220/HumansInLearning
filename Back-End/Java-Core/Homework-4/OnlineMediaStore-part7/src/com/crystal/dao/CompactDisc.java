package com.crystal.dao;

import java.util.ArrayList;

public class CompactDisc extends Media implements Playable {
    private final String artist;
    private final ArrayList<Track> tracks;

    public CompactDisc (String title, String category, double cost, String artist, ArrayList<Track> tracks) {
        super(title, category, cost);
        this.artist = artist;
        this.tracks = tracks;
    }

    public CompactDisc (CompactDisc cd) {
        super(cd.getTitle(), cd.getCategory(), cd.getCost());
        this.artist = cd.getArtist();
        this.tracks = cd.getTracks();
    }

    public String getArtist() {
        return artist;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public String toString() {
        return super.toString() + "\nArtist: " + this.artist + "\nTracks:" + this.tracks + "\n";
    }

    public void play() {
        System.out.println("Music album " + super.getTitle() + " plays!");
    }
}
