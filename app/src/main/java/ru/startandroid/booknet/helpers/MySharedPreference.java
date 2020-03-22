package ru.startandroid.booknet.helpers;


import android.content.Context;
import android.content.SharedPreferences;

import ru.startandroid.booknet.constants.AppConstants;

public class MySharedPreference {

    private SharedPreferences prefs;

    private Context context;

    public MySharedPreference(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(AppConstants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public void addProductToTheCart(String product) {
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(AppConstants.PRODUCT_ID, product);
        edits.apply();
    }

    public String retrieveProductFromCart() {
        return prefs.getString(AppConstants.PRODUCT_ID, "");
    }

    public void addProductCount(int productCount) {
        SharedPreferences.Editor edits = prefs.edit();
        edits.putInt(AppConstants.PRODUCT_COUNT, productCount);
        edits.apply();
    }

    public int retrieveProductCount() {
        return prefs.getInt(AppConstants.PRODUCT_COUNT, 0);
    }
}
