package com.example.crim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_screen extends AppCompatActivity {

    Button login;
    EditText email_login, password_login;
    TextView backtoreg;
    ProgressBar pgrbar;
    int clicks = 0;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        login = findViewById(R.id.login);
        email_login = findViewById(R.id.email_login);
        password_login = findViewById(R.id.password_login);
        backtoreg = findViewById(R.id.backtoreg);
        mAuth = FirebaseAuth.getInstance();
        pgrbar = findViewById(R.id.prgbar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email_login.getText().toString().trim().isEmpty()) {
                    Toast.makeText(login_screen.this, "Please Enter a valid email id", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password_login.getText().toString().trim().isEmpty() || password_login.getText().toString().trim().length() < 6) {
                    Toast.makeText(login_screen.this, "Please Enter a valid password", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //FireBase Login HERE
                    String email = email_login.getText().toString().trim();
                    String password = password_login.getText().toString().trim();
                    pgrbar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login_screen.this, "Login Successfull!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), homepage.class));
                                finish();
                            } else {
                                pgrbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(login_screen.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

        backtoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg = new Intent(login_screen.this, register_screen.class);
                startActivity(reg);

                //Toast.makeText(login_screen.this, "work in progress", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
