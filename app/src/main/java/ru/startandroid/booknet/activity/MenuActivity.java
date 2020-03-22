package ru.startandroid.booknet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.adapters.ShopRecyclerViewAdapter;
import ru.startandroid.booknet.helpers.SpacesItemDecoration;
import ru.startandroid.booknet.models.Book;
import ru.startandroid.booknet.utilities.ActivityUtilities;

public class MenuActivity extends BaseActivity {

    private static final int LAYOUT = R.layout.activity_menu;

    private RecyclerView shoppingRecyclerView;
    private Animation animation;

    private long backPressedMillis;
    private Context context;
    private Activity activity;
    private DrawerLayout drawerLayout;
    private VideoView videoView;
    private ImageView imageView, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        activity = MenuActivity.this;
        context = getApplicationContext();

        image = (ImageView) findViewById(R.id.image);
        imageView = (ImageView) findViewById(R.id.recent_news_image);
        videoView = (VideoView) findViewById(R.id.video);
        shoppingRecyclerView = (RecyclerView) findViewById(R.id.rvContent);
        shoppingRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false));
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));
        animation = AnimationUtils.loadAnimation(this, R.anim.zoomin);

        ShopRecyclerViewAdapter shopAdapter = new ShopRecyclerViewAdapter(MenuActivity.this, getAllProductsOnSale());
        shoppingRecyclerView.setAdapter(shopAdapter);

        initToolbar(getString(R.string.app_name));
        initNavigationView();

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                videoPlay(v);
//                backPressedMillis = System.currentTimeMillis();
//                if (backPressedMillis + 2000)
//                imageView.setVisibility(View.INVISIBLE);
//                    image.startAnimation(animation);
//                    image.setVisibility(View.VISIBLE);
//            }
//        });
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

    public void videoPlay(View v) {
        String pathVideo = "android.resource://ru.startandroid.booknet/" + R.raw.one;
        Uri uri = Uri.parse(pathVideo);
        videoView.setVideoURI(uri);
        videoView.start();
        imageView.setVisibility(View.INVISIBLE);
    }

    private List<Book> getAllProductsOnSale() {
        List<Book> products = new ArrayList<Book>();
        products.add(new Book("Sleek Black Top", 34, R.drawable.one, "Beautiffit and evenilk", "Classik", "Russia"));
        products.add(new Book("Flare Black Gown", 56, R.drawable.two, "Beautiful sleek btfit and evening walk", "Classik", "USA"));
        products.add(new Book("Flare White Blouse", 76, R.drawable.three, "Beautiful slit  evening walk", "Classik", "Russia"));
        products.add(new Book("Blue Swed Gown", 23, R.drawable.four, "Beautiful sleekasual outfit and evening walk", "Classik", "Europe"));
        products.add(new Book("Spotted Gown", 14, R.drawable.five, "Beautfit and evewalk", "Classik", "Russia"));
        return products;
    }
}
