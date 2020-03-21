package ru.startandroid.booknet.models;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.booknet.models.Book;

import static ru.startandroid.booknet.constants.Constants.*;

public class User{
    private static String name;
    private static String email;
    private static int age;
    private static List<Book> userStoryList = new ArrayList<>();
    private static List<String> interestsList = new ArrayList<>();
    private static List<Book> favoriteList = new ArrayList<>();

    public User(){}

    public User(String email) {
        User.email = email;
    }

    public static void setName(String name) {
        User.name = name;
    }
    public static String getName() {
        return name;
    }
    public static List<Book> getFavoriteList() {
        return favoriteList;
    }
    public static void setAge(int age) {
        if(age>0){
            if(age<200) {
                User.age = age;
            }
        }
    }
    public static int getAge() {
        return age;
    }
    public static List<String> getInterestsList() {
        return interestsList;
    }
    public static List<Book> getUserStoryList() {
        return userStoryList;
    }
    public static void addInterest(String interest){
        interestsList.add(interest);
    }
    public static void rateBook(Book book, float rate){
        book.getRateData().add(rate);
        int sum = 0;
        for(Float f: book.getRateData()){
            sum+=f;
        }
        book.setRating(sum/book.getRateData().size());
        REFERENCE_BOOK.child(book.getId()).child("rating").setValue(book.getRating());
    }
    public static  void markBookAsFavorite(Book book){
        favoriteList.add(book);
    }
    public static boolean isFavorite(Book book){
        return favoriteList.contains(book);
    }
    public static boolean downloadUserData(){
        //Загрузка данных из базы
        return false;
    }


}
