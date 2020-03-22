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
    private float rating;

    public Book(String name, String author, String genre) {
        this.author = author;
        this.genre = genre;
        this.name = name;
        this.id=name+"-"+author+"-"+genre;
    }

    public Book(){}

    public void setReferenceToBook(URL url){
        this.referenceToBook = url;
    }
    public void setCountry(String country){ this.country = country; }

    public String getId() {
        return this.id;
    }

    public List<Float> getRateData() {
        return rateData;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating){
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
