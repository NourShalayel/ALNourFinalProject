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
        name = findViewById(R.id.cus_name);
        phone = findViewById(R.id.cus_phonenumber);
        email = findViewById(R.id.cus_email);
        address = findViewById(R.id.cus_address);
    }
}