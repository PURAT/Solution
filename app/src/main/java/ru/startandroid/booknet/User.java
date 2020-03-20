package ru.startandroid.booknet;

import java.util.ArrayList;
import java.util.List;

public class User {
    private  String name;
    private int age;
    private List<Book> userStoryList = new ArrayList<>();

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<String> getInterestsList() {
        return interestsList;
    }

    private List<String> interestsList = new ArrayList<>();
    private List<Book> favoriteList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Book> getUserStoryList() {
        return userStoryList;
    }

    public int rateBook(Book book, int rate){
        book.getRateData().add(rate);
        int sum = 0;
        for(Integer i: book.getRateData()){
            sum+=i;
        }
        int rating = sum/book.getRateData().size();
        return rating;
    }
    public void markAsFavotite(Book book){
        this.favoriteList.add(book);
    }
    public boolean isFavorite(Book book){
        return this.favoriteList.contains(book);
    }


}
