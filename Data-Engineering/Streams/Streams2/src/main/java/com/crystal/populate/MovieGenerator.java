package com.crystal.populate;

import com.crystal.dao.Genre;
import com.crystal.dao.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MovieGenerator {

    private final List<String> regularWord;
    private final List<String> linkWord;

    DirectorGenerator directorGenerator;

    Random random;

    public MovieGenerator() {
        regularWord = Arrays.asList("Hunter", "Intruder", "Figure", "Creature", "Foreigner", "Armies", "Men", "Women", "Recruits", "Pilots", "Directors",
                "Restoration", "Demise", "Throne", "Universe", "Celebrating", "Caution", "Puzzle", "Fortune", "Equality");
        linkWord = Arrays.asList("with", "of", "and", "for", "for the");
        directorGenerator = new DirectorGenerator();
        random = new Random();
    }

    public List<Movie> generateMovieList(int size) {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            movies.add(generateRandomMovie());
        }

        return movies;
    }

    public Movie generateRandomMovie() {
        String movieTitle = regularWord.get(random.nextInt(regularWord.size())) + " " +
                linkWord.get(random.nextInt(linkWord.size())) + " " +
                regularWord.get(random.nextInt(regularWord.size()));

        return new Movie(movieTitle,
                random.nextInt(70) + 1950,
                random.nextInt(100000),
                directorGenerator.generateDirector(),
                generateGenresList());
    }

    private List<Genre> generateGenresList() {
        List<Genre> genres = new ArrayList<>();
        for(int i = 0; i < random.nextInt(3) + 1; i++) {
            genres.add(Genre.randomGenre());
        }
        return genres;
    }
}
