package com.example;

import java.util.ArrayList;
import java.util.Random;

/**
 * Jokes Source
 *
 * https://www.buzzfeed.com/jessicamisener/21-jokes-so-stupid-theyre-actually-funny?utm_term=.txrVWOp2r#.rqpbejL3l
 */
public class Jokes {

    private ArrayList<String> jokeLists = new ArrayList<>();

    public Jokes() {
        fetchJokes();
    }

    private void fetchJokes() {
        jokeLists.add("My sister bet me a hundred dollars I couldn’t build a car out of spaghetti");
        jokeLists.add("How many South Americans does it take to change a lightbulb?");
        jokeLists.add("What time does Sean Connery get to Wimbledon");
        jokeLists.add("I went to the zoo the other day. It was empty, except for a single dog");
        jokeLists.add("What kind of bagel can fly?");
        jokeLists.add("Where do animals go when their tails fall off?");
    }

    /**
     * Get all jokes that already define
     *
     * @return ArrayList<String> jokes
     */
    public ArrayList<String> allJokes () {
        return jokeLists;
    }

    /**
     * Get random Jokes from the collection
     *
     * @return String joke
     */
    public String getJoke () {
        int randomNumber = randomNumber(jokeLists.size() - 1, 0);
        return jokeLists.get(randomNumber);
    }

    private int randomNumber (int max, int min) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
