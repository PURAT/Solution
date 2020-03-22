package ru.startandroid.booknet.models;

import android.media.Image;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String id;
    private String author;
    private String genre;
    private String country;
    private String name;
    private double price;
    private List<Float> rateData = new ArrayList<>();
    private int rating;
    private int image;
    private URL url;

    public Book(String name, double price, int image, String author, String genre, String country) {
        this.author = author;
        this.genre = genre;
        this.country = country;
        this.name = name;
        this.id = name + author + genre;
        this.image = image;
        this.price = price;
    }

    public Book() {
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

    public void setRating(int rating) {
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

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setReferenceToBook(URL url) {
        this.url = url;
    }
    public URL getUrl(){
        return this.url;
    }
}
