package com.example.crim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_screen extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText emailid,passwd,name,phone;

    Spinner spinner;
    TextView backtologin;
    Button regist;
    String [] code = {"Home District","Belgaum","Bagalkot"};
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        regist= findViewById(R.id.register);
        backtologin=findViewById(R.id.backtologin);
        mAuth = FirebaseAuth.getInstance();
        spinner= findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);



        emailid = findViewById(R.id.email_reg);
        passwd = findViewById(R.id.pass_reg);
        phone = findViewById(R.id.phone_reg);
        name = findViewById(R.id.reg_name);

        if(mAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        ArrayAdapter<String> adapterspinner = new ArrayAdapter<>(this,R.layout.spinner,code);
        adapterspinner.setDropDownViewResource(R.layout.spinner);
        spinner.setAdapter(adapterspinner);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(register_screen.this,"Work in progress",Toast.LENGTH_SHORT).show();
                String email = emailid.getText().toString().trim();
                String password = passwd.getText().toString().trim();

                if(email.isEmpty())
                {
                    emailid.setError("Email is Required");
                    return;
                }
                else if(password.isEmpty())
                {
                    passwd.setError("Password is Required");
                    return;
                }
                else if(phone.length()==0 || phone.length()!=10){
                    phone.setError("Enter Valid Phone Number");
                    return;
                }
                else if(name.length()==0){
                    name.setError("Enter Valid Name");
                    return;
                }
                else if(password.length()<6)
                {
                    passwd.setError("Password Length should be greater than 6");
                    return;
                }



                //Register for FireBase

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(register_screen.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(register_screen.this,login_screen.class));
                        }
                        else
                        {
                            Toast.makeText(register_screen.this, "Error:"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log = new Intent(register_screen.this,login_screen.class);
                startActivity(log);
                finish();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
