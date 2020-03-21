package ru.startandroid.booknet.models;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private URL referenceToBook;
    private String id;
    private String author;
    private String genre;
    private String country;
    private String name;
    private List<Float> rateData = new ArrayList<>();
    private int rating;

    public Book(String name, String author, String genre, String country) {
        this.author = author;
        this.genre = genre;
        this.country = country;
        this.name = name;
        this.id=name+"-"+author+"-"+genre;
    }

    public Book(){}

    public void setReferenceToBook(URL url){
        this.referenceToBook = url;
    }
    public String getId() {
        return this.id;
    }

    public List<Float> getRateData() {
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
