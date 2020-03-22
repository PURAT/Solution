package ru.startandroid.booknet.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.adapters.CheckRecyclerViewAdapter;
import ru.startandroid.booknet.helpers.MySharedPreference;
import ru.startandroid.booknet.helpers.SimpleDividerItemDecoration;
import ru.startandroid.booknet.models.Book;
import ru.startandroid.booknet.utilities.ActivityUtilities;

public class CheckoutActivity extends BaseActivity {

    private static final String TAG = CheckoutActivity.class.getSimpleName();

    private Activity mActivity;
    private RecyclerView checkRecyclerView;
    private TextView subTotal;
    private double mSubTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        subTotal = (TextView) findViewById(R.id.sub_total);

        checkRecyclerView = (RecyclerView) findViewById(R.id.rvContent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
        checkRecyclerView.setLayoutManager(linearLayoutManager);
        checkRecyclerView.setHasFixedSize(true);
        checkRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(CheckoutActivity.this));

        // get content of cart
        MySharedPreference mShared = new MySharedPreference(CheckoutActivity.this);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Book[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), Book[].class);
        List<Book> productList = convertObjectArrayToListObject(addCartProducts);

        CheckRecyclerViewAdapter mAdapter = new CheckRecyclerViewAdapter(CheckoutActivity.this, productList);
        checkRecyclerView.setAdapter(mAdapter);

        mSubTotal = getTotalPrice(productList);
        subTotal.setText("Total: " + String.valueOf(mSubTotal) + " $");

        Button checkButton = (Button) findViewById(R.id.checkout);
        assert checkButton != null;
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckoutActivity.this, "Эта опция пока не доступна.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mActivity = CheckoutActivity.this;
        setContentView(R.layout.activity_checkout);

        initToolbar(getString(R.string.basket));
        enableUpButton();
    }

    private List<Book> convertObjectArrayToListObject(Book[] allProducts) {
        List<Book> mProduct = new ArrayList<Book>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private int returnQuantityByProductName(String productName, List<Book> mProducts) {
        int quantityCount = 0;
        for (int i = 0; i < mProducts.size(); i++) {
            Book pObject = mProducts.get(i);
            if (pObject.getName().trim().equals(productName.trim())) {
                quantityCount++;
            }
        }
        return quantityCount;
    }

    private double getTotalPrice(List<Book> mProducts) {
        double totalCost = 0;
        for (int i = 0; i < mProducts.size(); i++) {
            Book pObject = mProducts.get(i);
            totalCost = totalCost + pObject.getPrice();
        }
        return totalCost;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityUtilities.getInstance().invokeNewActivity(mActivity, MenuActivity.class, true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ActivityUtilities.getInstance().invokeNewActivity(mActivity, MenuActivity.class, true);
    }
}
