package ru.startandroid.booknet.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.utilities.ActivityUtilities;

public class ActivityItem3 extends BaseActivity {

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mActivity = ActivityItem3.this;
        setContentView(R.layout.activity_item3);

        initToolbar(getString(R.string.menu_item_3));
        enableUpButton();
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
