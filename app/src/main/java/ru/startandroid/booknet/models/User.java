package ru.startandroid.booknet.models;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.booknet.models.Book;

public class User {
    private String name;
    private String surname;
    private int age;
    private String password;
    private String email;

    private List<String> interestsList = new ArrayList<>();
    private List<Book> favoriteList = new ArrayList<>();
    private List<Book> userStoryList = new ArrayList<>();

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getInterestsList() {
        return interestsList;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Book> getUserStoryList() {
        return userStoryList;
    }

    public void rateBook(Book book, int rate) {
        book.getRateData().add(rate);
        int sum = 0;
        for (Integer i : book.getRateData()) {
            sum += i;
        }
        book.setRating(sum / book.getRateData().size());
    }

    public void markAsFavotite(Book book) {
        this.favoriteList.add(book);
    }

    public boolean isFavorite(Book book) {
        return this.favoriteList.contains(book);
    }


}
