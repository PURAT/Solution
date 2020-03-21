package ru.startandroid.booknet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.startandroid.booknet.R;
import ru.startandroid.booknet.activity.EmailPasswordActivity;
import ru.startandroid.booknet.models.User;

public class MainActivity extends AppCompatActivity {
    public static User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = (Button)findViewById(R.id.exButton);
        setContentView(R.layout.activity_main);
    }
    public void goSigning(View view){
        Intent intent = new Intent(this, EmailPasswordActivity.class);
        startActivity(intent);
    }


}
