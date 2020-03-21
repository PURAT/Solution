package ru.startandroid.booknet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseTestActivity extends AppCompatActivity {
    EditText bookName;
    EditText bookAuthor;
    EditText bookCountry;
    Button addButton;
    DatabaseReference databaseBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);

        databaseBook = FirebaseDatabase.getInstance().getReference("books");

        // Read from the database
        databaseBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
               // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        bookName = (EditText)findViewById(R.id.bookName);
        bookAuthor = (EditText)findViewById(R.id.bookAuthor);
        bookCountry = (EditText)findViewById(R.id.bookCountry);
        addButton = (Button) findViewById(R.id.addButton);

    }
    public void onClickAddBook(View view){
        String name = bookName.getText().toString().trim();
        String author = bookAuthor.getText().toString();
        String country = bookCountry.getText().toString();

            String id = databaseBook.push().getKey();
            Book book = new Book(id, name,author,"Science",country);
            databaseBook.child(id).setValue(book);
            System.out.println(name+"это имя");
            System.out.println(author+"это author");
            System.out.println(country+"это country");
    }
}
