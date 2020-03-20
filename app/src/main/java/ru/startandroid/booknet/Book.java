package ru.startandroid.booknet;

public class Book {
    private String author;
    private String genre;
    private String country;
    private String name;
    private int rating;

    public Book(String name, String author, String genre, String country) {
        this.author = author;
        this.genre = genre;
        this.country = country;
        this.name = name;
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
}
