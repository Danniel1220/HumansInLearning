package com.crystal.dao;

import java.util.Random;

public enum Genre {
    Drama, Horror, Action, Comedy, Thriller, SF, Romance, Western, Crime, Adventure,
    Musical, Animation, Fantasy, RomCom, Documentary, Historical, Indie, ForChildren;

    public static Genre randomGenre() {
        return Genre.values()[new Random().nextInt(Genre.values().length)];
    }
}
