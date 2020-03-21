package ru.startandroid.booknet.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String id;
    private String author;
    private String genre;
    private String country;
    private String name;
    private List<Integer> rateData = new ArrayList<>();
    private int rating;

    public Book(String name, String author, String genre, String country) {
        this.author = author;
        this.genre = genre;
        this.country = country;
        this.name = name;
        this.id=name+author+genre;
    }

    public Book(){}

    public String getId() {
        return this.id;
    }

    public List<Integer> getRateData() {
        return rateData;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating){
        this.rating = rating;
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
