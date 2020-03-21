package ru.startandroid.booknet.constants;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.startandroid.booknet.models.User;

public class Constants {
public static FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();
public static DatabaseReference REFERENCE_BOOK = DATABASE.getReference("Books");
public static DatabaseReference REFERENCE_USER = DATABASE.getReference("Users");
public static User CURRENT_USER;
}
