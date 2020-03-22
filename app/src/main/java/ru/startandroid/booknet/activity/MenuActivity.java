package ru.startandroid.booknet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.adapters.ShopRecyclerViewAdapter;
import ru.startandroid.booknet.helpers.SpacesItemDecoration;
import ru.startandroid.booknet.models.Book;
import ru.startandroid.booknet.models.User;

import static ru.startandroid.booknet.constants.Constants.CURRENT_USER;
import static ru.startandroid.booknet.constants.Constants.REFERENCE_BOOKS;
import static ru.startandroid.booknet.constants.Constants.REFERENCE_USERS;

public class MenuActivity extends BaseActivity {

    private static final int LAYOUT = R.layout.activity_menu;

    private RecyclerView shoppingRecyclerView;

    private Context context;
    private Activity activity;
    private DrawerLayout drawerLayout;
    List<Book> products;

    ListView bookListView;
    ArrayList<Book> aaa;
    ArrayList<Book> booksList;
    ArrayList<String> itemList;
    ShopRecyclerViewAdapter shopAdapter;
    Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        activity = MenuActivity.this;
        context = getApplicationContext();

        addBookWindowShow();

        shoppingRecyclerView = (RecyclerView) findViewById(R.id.rvContent);
        shoppingRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false));
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));

        //load all books
        book = new Book();
        booksList = new ArrayList<>();
        shopAdapter = new ShopRecyclerViewAdapter(MenuActivity.this, booksList);
        REFERENCE_BOOKS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    book = ds.getValue(Book.class);
                    booksList.add(book);
                }
                shoppingRecyclerView.setAdapter(shopAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        initToolbar(getString(R.string.app_name));
        initNavigationView();
//        initListener();
//        loadData();

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
                        Intent intent = new Intent(MenuActivity.this, CheckoutActivity.class);
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
    private void addBookWindowShow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Добавить книгу");
        dialog.setMessage("Заполните поля о книге");

        LayoutInflater flater = LayoutInflater.from(this);
        View add_books_window = flater.inflate(R.layout.add_books_window, null);
        dialog.setView(add_books_window);

        final MaterialEditText name = add_books_window.findViewById(R.id.book_name_field);
        final MaterialEditText author = add_books_window.findViewById(R.id.book_author_field);
        final MaterialEditText genre = add_books_window.findViewById(R.id.book_genre_field);
        final MaterialEditText url = add_books_window.findViewById(R.id.book_url_field);
        final MaterialEditText price = add_books_window.findViewById(R.id.book_price_field);
        final MaterialEditText country = add_books_window.findViewById(R.id.book_country_field);
        final MaterialEditText int_img = add_books_window.findViewById(R.id.book_img_int_field);

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
                final String priceText = price.getText().toString();
                final String countryText = country.getText().toString();
                final int img = Integer.parseInt(int_img.getText().toString());

                if (TextUtils.isEmpty(nameText)) {
                    //Snackbar.make(root, "Введите название книги", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(authorText)) {
                    //Snackbar.make(root, "Введите имя автора", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(genreText)) {
                    //Snackbar.make(root, "Введите жанр произведения", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(urlText)) {
                    //Snackbar.make(root, "Введите ссылку на электронный вариант книги", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(priceText)) {
                   // Snackbar.make(root, "Введите цену", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(countryText)) {
                    //Snackbar.make(root, "Введите страну", Snackbar.LENGTH_LONG).show();
                    return;
                }


                Book book = new Book(nameText,Integer.parseInt(priceText), img, authorText,genreText,countryText);
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

   /* private void showAccountWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Аккаунт");
        dialog.setMessage("О пользователе");

        LayoutInflater flater = LayoutInflater.from(this);
        View account_info_window = flater.inflate(R.layout.account_info_window, null);
        dialog.setView(account_info_window);

        final TextView name = account_info_window.findViewById(R.id.user_name);
        final TextView email = account_info_window.findViewById(R.id.user_email);
        final MaterialEditText interests = account_info_window.findViewById(R.id.interests);


        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String interestsText = interests.getText().toString();

                String[] interestsArray = interestsText.split(",");

                ArrayList<String> interestlist = new ArrayList<>();
                for(String s: interestsArray){
                    interestlist.add(s);
                }
                User user = CURRENT_USER;
                user.setInterestsList(interestlist);
                REFERENCE_USERS.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).setValue(user);
            }
        });

        dialog.show();
    }*/

}
