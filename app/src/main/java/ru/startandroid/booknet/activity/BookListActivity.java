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
        book = new Book();
        bookListView = (ListView) findViewById(R.id.book_list_view);
        itemList = new ArrayList<>();
        booksList = new ArrayList<>();
        booksAdapter = new ArrayAdapter<String>(this,R.layout.book_info_item,R.id.bookInfo, itemList);
        REFERENCE_BOOKS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                       book = ds.getValue(Book.class);
                       booksList.add(book);
                       itemList.add("Название: "+book.getName()+"\n"+"Автор: "+book.getAuthor()+"\n"+"Жанр: "+book.getGenre());
                }
                bookListView.setAdapter(booksAdapter);
                System.out.println(itemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }


}
