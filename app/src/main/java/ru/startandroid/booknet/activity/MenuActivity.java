package ru.startandroid.booknet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.utilities.ActivityUtilities;

public class MenuActivity extends BaseActivity {

    private static final int LAYOUT = R.layout.activity_menu;

    private Context context;
    private Activity activity;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        activity = MenuActivity.this;
        context = getApplicationContext();

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
}
