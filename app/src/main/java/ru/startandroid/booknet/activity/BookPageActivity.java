package ru.startandroid.booknet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.models.Book;
import static ru.startandroid.booknet.constants.Constants.*;

public class BookPageActivity extends AppCompatActivity {
    private TextView bookname;
    private RatingBar ratingBar;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_page);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        bookname = (TextView)findViewById(R.id.bookName);
        Intent intent = getIntent();
        String bookId = intent.getStringExtra("bookId");

        REFERENCE_BOOK.child(bookId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                book = dataSnapshot.getValue(Book.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                MainActivity.user.rateBook(book,rating);
            }
        });
    }

}
