package com.example.alnour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateSupplierActivity extends AppCompatActivity {
    public TextInputEditText name, phone, email , address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_supplier);
        init();
    }
    public void init(){
        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phonenumber);
        email = findViewById(R.id.edit_email);
        address = findViewById(R.id.edit_address);
    }
}