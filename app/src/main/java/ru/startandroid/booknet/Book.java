package ru.startandroid.booknet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private String author;
    private String genre;
    private String country;
    private String name;

    public List<Integer> getRateData() {
        return rateData;
    }

    public int getRating() {
        return rating;
    }

    private List<Integer> rateData = new ArrayList<>();
    private int rating;

    public Book(String name, String author, String genre, String country) {
        this.author = author;
        this.genre = genre;
        this.country = country;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }
}
