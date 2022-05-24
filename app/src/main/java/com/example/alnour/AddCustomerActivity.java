package com.example.alnour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class AddCustomerActivity extends AppCompatActivity {
    public TextInputEditText name, phone, email , address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        init();

    }
    public void init(){
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
    }
}