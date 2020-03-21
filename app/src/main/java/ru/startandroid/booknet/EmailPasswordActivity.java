package ru.startandroid.booknet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class EmailPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button signButton;
    private EditText email;
    private EditText password;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);
        mAuth = FirebaseAuth.getInstance();
        signButton= (Button)findViewById(R.id.signButton);
        email = (EditText)findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        intent = new Intent(this, FireBaseTestActivity.class);
    }

    public void onClickSign(View view){
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        createAccount(emailText,passwordText);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(EmailPasswordActivity.this,"Успешно",Toast.LENGTH_SHORT).show();
                            //without UI
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(EmailPasswordActivity.this,"Не успешно",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void singin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(EmailPasswordActivity.this,"Успешно",Toast.LENGTH_SHORT);
                            //without UI
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(EmailPasswordActivity.this,"Не успешно",Toast.LENGTH_SHORT);
                            //without UI
                        }
                    }
                });
    }

    private void getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }
}
