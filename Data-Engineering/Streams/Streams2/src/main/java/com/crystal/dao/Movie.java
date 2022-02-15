package com.crystal.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
public class Movie {
    private final String title;
    private final int year;
    private final long id;
    private final Director director;
    private final List<Genre> genres;
}
