package com.example.alnour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateSupplierActivity extends AppCompatActivity {
    public TextInputEditText name, phone, email , address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

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