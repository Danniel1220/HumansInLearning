package com.crystal.dao;

import java.util.ArrayList;

public class Library<T> {
    public ArrayList<T> addTrack (ArrayList<T> list, T track) {
        ArrayList<T> newList = new ArrayList<>(list);
        newList.add(track);
        return newList;
    }

    public ArrayList<T> removeTrack (ArrayList<T> list, int index) {
        ArrayList<T> newList = new ArrayList<>(list);
        newList.remove(index);
        return newList;
    }
}
