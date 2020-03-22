package ru.startandroid.booknet.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.helpers.MySharedPreference;
import ru.startandroid.booknet.models.Book;
import ru.startandroid.booknet.utilities.ActivityUtilities;

public class ProductActivity extends BaseActivity {
    private Activity mActivity;
    private TextView productSize, productColor, productPrice, productDescription;

    private ImageView productImage;
    private MySharedPreference sharedPreference;

    private Gson gson;

    private int cartProductNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        sharedPreference = new MySharedPreference(ProductActivity.this);
        productImage = (ImageView) findViewById(R.id.full_product_image);
        productSize = (TextView) findViewById(R.id.product_size);
        productColor = (TextView) findViewById(R.id.product_color);
        productPrice = (TextView) findViewById(R.id.product_price);
        productDescription = (TextView) findViewById(R.id.product_description);

        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();

        String productInStringFormat = getIntent().getExtras().getString("PRODUCT");
        final Book singleProduct = gson.fromJson(productInStringFormat, Book.class);
        if (singleProduct != null) {
            productImage.setImageResource(singleProduct.getImage());
            productSize.setText("Author: " + String.valueOf(singleProduct.getAuthor()));
            productColor.setText("Genre: " + singleProduct.getGenre());
            productPrice.setText("Price: " + String.valueOf(new Double(singleProduct.getPrice()).intValue()) + " $");
            productDescription.setText(Html.fromHtml("<strong>Product Description</strong><br/>" + singleProduct.getName()));
        }

        Button addToCartButton = (Button) findViewById(R.id.add_to_cart);
        assert addToCartButton != null;
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //increase product count
                String productsFromCart = sharedPreference.retrieveProductFromCart();
                if (productsFromCart.equals("")) {
                    List<Book> cartProductList = new ArrayList<Book>();
                    cartProductList.add(singleProduct);
                    String cartValue = gson.toJson(cartProductList);
                    sharedPreference.addProductToTheCart(cartValue);
                    cartProductNumber = cartProductList.size();
                } else {
                    String productsInCart = sharedPreference.retrieveProductFromCart();
                    Book[] storedProducts = gson.fromJson(productsInCart, Book[].class);

                    List<Book> allNewProduct = convertObjectArrayToListObject(storedProducts);
                    allNewProduct.add(singleProduct);
                    String addAndStoreNewProduct = gson.toJson(allNewProduct);
                    sharedPreference.addProductToTheCart(addAndStoreNewProduct);
                    cartProductNumber = allNewProduct.size();
                }
                sharedPreference.addProductCount(cartProductNumber);
                invalidateCart();
            }
        });
    }

    private void initView() {
        mActivity = ProductActivity.this;
        setContentView(R.layout.activity_product);
        initToolbar(getString(R.string.product));
        enableUpButton();
    }

    private List<Book> convertObjectArrayToListObject(Book[] allProducts) {
        List<Book> mProduct = new ArrayList<Book>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_shop);
        int mCount = sharedPreference.retrieveProductCount();
        menuItem.setIcon(buildCounterDrawable(mCount, R.drawable.cart));
        return true;
    }

    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.shopping_layout, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private void invalidateCart() {
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                ActivityUtilities.getInstance().invokeNewActivity(mActivity, MenuActivity.class, true);
                return true;
            case R.id.action_shop:
                ActivityUtilities.getInstance().invokeNewActivity(mActivity, CheckoutActivity.class, true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ActivityUtilities.getInstance().invokeNewActivity(mActivity, MenuActivity.class, true);
    }

}
