package ru.startandroid.booknet.models;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.booknet.models.Book;

public class User{
    private String name;
    private String email;
    private int age;
    private List<Book> userStoryList = new ArrayList<>();
    private List<String> interestsList = new ArrayList<>();
    private List<Book> favoriteList = new ArrayList<>();

    public User(){}

    public User(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public List<Book> getFavoriteList() {
        return favoriteList;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public List<String> getInterestsList() {
        return interestsList;
    }

    public List<Book> getUserStoryList() {
        return userStoryList;
    }

    public void addInterest(String interest){
        this.interestsList.add(interest);
    }
    public void rateBook(Book book, int rate){
        book.getRateData().add(rate);
        int sum = 0;
        for(Integer i: book.getRateData()){
            sum+=i;
        }
        book.setRating(sum/book.getRateData().size());
    }
    public void markBookAsFavorite(Book book){
        this.favoriteList.add(book);
    }
    public boolean isFavorite(Book book){
        return this.favoriteList.contains(book);
    }


}
