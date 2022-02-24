package com.crystal.populate;

import com.crystal.dao.Director;
import com.crystal.dao.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DirectorGenerator {
    List<String> firstNames;
    List<String> lastNames;

    public DirectorGenerator() {
        firstNames = Arrays.asList("John", "David", "Elisabeth", "Daniel", "Andrew", "Maria", "Diana", "Nicholas", "Sarah", "Miley");
        lastNames = Arrays.asList("Cooper", "Smith", "Ackerman", "Jaeger", "Williams", "Brown", "Garcia", "Miller");
    }

    public List<Director> generateDirectorList(int size) {
        List<Director> directors = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            directors.add(generateDirector());
        }
        return directors;
    }

    public Director generateDirector() {
        Random random = new Random();
        String name = firstNames.get(random.nextInt(firstNames.size() - 1)) + " "
                + lastNames.get(random.nextInt(lastNames.size() - 1));

        return new Director(name);
    }
}
