package ru.startandroid.booknet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import ru.startandroid.booknet.R;

import static ru.startandroid.booknet.constants.Constants.*;

public class BookRateActivity extends AppCompatActivity {
    private TextView bookname;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_rate);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        bookname = (TextView)findViewById(R.id.bookName);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                CURRENT_USER.rateBook(id, float);
            }
        });
    }

}
