package ru.startandroid.booknet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.models.Book;
import ru.startandroid.booknet.utilities.ActivityUtilities;

import static ru.startandroid.booknet.constants.Constants.*;

public class MenuActivity extends BaseActivity {

    private static final int LAYOUT = R.layout.activity_menu;

    private Context context;
    private Activity activity;
    private DrawerLayout drawerLayout;
    private RelativeLayout root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        activity = MenuActivity.this;
        context = getApplicationContext();
        root = findViewById(R.id.add_books_window);
//        recyclerView = (RecyclerView) findViewById(R.id.rvContent);
//        recyclerView.setLayoutManager(new GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false));
//
//        categoryList = new ArrayList<>();
//        adapter = new CategoryAdapter(context, activity, categoryList);
//        recyclerView.setAdapter(adapter);

        initToolbar(getString(R.string.app_name));
        initNavigationView();
//        initListener();
//        loadData();

        showWindowAddBook();
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.actionItem1:
                        break;
                    case R.id.actionItem2:
                        Intent intent = new Intent(MenuActivity.this, ActivityItem2.class);
                        startActivity(intent);
                        break;
                    case R.id.actionItem3:
                        intent = new Intent(MenuActivity.this, ActivityItem3.class);
                        startActivity(intent);
                        break;
                    case R.id.actionItem4:
                        intent = new Intent(MenuActivity.this, ActivityItem4.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

    private void showWindowAddBook() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Добавить книгу");
        dialog.setMessage("Введите данные о книге");

        LayoutInflater flater = LayoutInflater.from(this);
        View add_book_window = flater.inflate(R.layout.add_books_window, null);
        dialog.setView(add_book_window);

        final MaterialEditText name = add_book_window.findViewById(R.id.book_name_field);
        final MaterialEditText author = add_book_window.findViewById(R.id.book_author_field);
        final MaterialEditText genre = add_book_window.findViewById(R.id.book_genre_field);
        final MaterialEditText url = add_book_window.findViewById(R.id.book_url_field);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String nameText = name.getText().toString();
                final String authorText = author.getText().toString();
                final String genreText = genre.getText().toString();
                final String urlText = url.getText().toString();


                if (TextUtils.isEmpty(nameText)) {
                    Snackbar.make(root, "Введите название книги", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(authorText)) {
                    Snackbar.make(root, "Введите имя автора", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(genreText)) {
                    Snackbar.make(root, "Введите жанр произведения", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(urlText)) {
                    Snackbar.make(root, "Введите ссылку на электронный вариант книги", Snackbar.LENGTH_LONG).show();
                    return;
                }


                Book book = new Book(nameText,authorText,genreText);
                try {
                    book.setReferenceToBook(new URL(urlText));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                REFERENCE_BOOKS.child(book.getId()).setValue(book);
            }
        });

        dialog.show();

    }

}
