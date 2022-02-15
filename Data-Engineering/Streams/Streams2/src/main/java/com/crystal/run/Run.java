package com.crystal.run;

import com.crystal.dao.Movie;
import com.crystal.populate.MovieGenerator;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<Movie> movies = new MovieGenerator().generateMovieList(100);
        System.out.println(movies);
    }
}
