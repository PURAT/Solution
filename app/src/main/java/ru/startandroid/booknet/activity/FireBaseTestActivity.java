package ru.startandroid.booknet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.models.*;


import static ru.startandroid.booknet.constants.Constants.*;

public class FireBaseTestActivity extends AppCompatActivity {
    EditText bookName;
    EditText bookAuthor;
    EditText bookCountry;
    EditText bookGenre;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);

        bookName = (EditText) findViewById(R.id.bookName);
        bookAuthor = (EditText) findViewById(R.id.bookAuthor);
        bookGenre = (EditText) findViewById(R.id.bookGenre);
        bookCountry = (EditText) findViewById(R.id.bookCountry);
        addButton = (Button) findViewById(R.id.addButton);
    }
//    public void onClickSend(View view){
//        String name = bookName.getText().toString();
//        String author = bookAuthor.getText().toString();
//        String genre = bookGenre.getText().toString();
//        String country = bookCountry.getText().toString();
//        Book book = new Book(name,author,genre,country);
//        REFERENCE_BOOK.child(book.getId()).setValue(book);
//    }
}
