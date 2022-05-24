package com.example.alnour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Spinner cat_list = findViewById(R.id.selectCategory );
        Spinner sup_list = findViewById(R.id.selectSupplier);
        String[] cat_items = { "Chai Latte", "Green Tea", "Black Tea" };
        String[] sup_items = { "Chai Latte", "Green Tea", "Black Tea" };

        ArrayAdapter<String> cat_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cat_items);

        ArrayAdapter<String> sup_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sup_items);

        cat_list.setAdapter(cat_adapter);
        sup_list.setAdapter(sup_adapter);

    }


}