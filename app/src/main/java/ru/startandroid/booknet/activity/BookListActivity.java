package ru.startandroid.booknet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.models.Book;

import static ru.startandroid.booknet.constants.Constants.*;

public class BookListActivity extends AppCompatActivity {
    ListView bookListView;
    ArrayList<Book> aaa;
    ArrayList<Book> booksList;
    ArrayList<String> itemList;
    ArrayAdapter<String> booksAdapter;
    Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        getDataBook();
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    public void getDataBook(){
    }

}
