package com.example.alnour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView txtlogin;
    FirebaseAuth firAuth ;

    private TextInputEditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_login);


        inti();
        lisner();

        Typeface typeface = getResources().getFont(R.font.bungee);
        txtlogin.setTypeface(typeface);

    }

    private void auth() {
        String email =  et_email.getText().toString();
        String password =  et_password.getText().toString();

        if (email.isEmpty()){
            Toast.makeText(this , "Email Empty !!" , Toast.LENGTH_SHORT).show();
        }else if (password.isEmpty()){
            Toast.makeText(this , "Password Empty !!" , Toast.LENGTH_SHORT).show();
        }else{
            firAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "email or password wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void lisner() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth();
            }
        });

    }

    private void inti() {
        firAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
        txtlogin = findViewById(R.id.txtlogin);
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);
    }
}