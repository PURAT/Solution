package ru.startandroid.booknet.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.models.Book;

import static ru.startandroid.booknet.constants.Constants.REFERENCE_BOOK;

public class Catalogue_Activity extends AppCompatActivity {
    TextView text1,text2,text3,text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue_);
        text1 = (TextView)findViewById(R.id.bookName);
        text2 = (TextView)findViewById(R.id.bookAuthor);
        text3 = (TextView)findViewById(R.id.bookCountry);
        text4 = (TextView)findViewById(R.id.bookID);

        REFERENCE_BOOK.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book = dataSnapshot.getValue(Book.class);
                text1.setText(book.getName());
                text2.setText(book.getAuthor());
                text3.setText(book.getCountry());
                text4.setText(book.getId());
                System.out.println("Title "+book.getName());
                System.out.println("Author "+book.getAuthor());
                System.out.println("Id "+s);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onClickGoBookPage(View view){
        Intent intent = new Intent(this,BookPageActivity.class);
        intent.putExtra("bookId",text4.getText().toString());
        startActivity(intent);

    }

}
