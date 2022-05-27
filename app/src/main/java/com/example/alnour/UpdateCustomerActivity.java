package com.example.alnour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateCustomerActivity extends AppCompatActivity {
    public TextInputEditText name, phone, email , address;
    Intent intent ;
    Bundle data ;
    String id ;
    Button updateCustomerbtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_update_category);

        super.onCreate(savedInstanceState);
        init();

        intent = getIntent();

         data = intent.getExtras();
        if (data != null) {
             id = data.getString("customerId");
            name.setText(data.getString("name"));
            phone.setText(data.getString("call"));
            email.setText(data.getString("email"));
            address.setText(data.getString("address"));
        } else {
            name.setText("");
            phone.setText("");
            email.setText("");
            address.setText("");
        }
        updateCustomerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputValidation()) {
                    if (UpdateCustomer()) {
//                        CustomerFragment customerFrag = new CustomerFragment();
//                        FragmentManager fm = getApplicationContext().getSupportFragmentManager();
//                        FragmentTransaction ft = fm.beginTransaction();
//                        ft.addToBackStack(null);
//                        ft.replace(R.id.fcv, customerFrag);
//                        ft.commit();
                    }
                }
            }
        });

    }
    public void init(){
        name = findViewById(R.id.edit_cus_name);
        phone = findViewById(R.id.edit_cus_phonenumber);
        email = findViewById(R.id.edit_cus_email);
        address = findViewById(R.id.edit_cus_address);
        updateCustomerbtn = findViewById(R.id.updateCustomerbtn);
    }
    public boolean UpdateCustomer(){
        DatabaseReference refCus = FirebaseDatabase.getInstance().getReference("customers").child(id);

        String cus_name = name.getText().toString();
        String cus_phone = phone.getText().toString();
        String cus_email = email.getText().toString();
        String cus_address = address.getText().toString();

        Person customer  = new Person(id , cus_name , cus_phone , cus_email , cus_address);
        refCus.setValue(customer);
        Toast.makeText(this, "Updated Successfully " , Toast.LENGTH_SHORT).show();

        return  true ;
    }


    public Boolean inputValidation() {
        boolean flag = true;

        if (name.getText().toString().isEmpty()) {
            name.setError("Can't be Empty");
            return false;
        }

        if (phone.getText().toString().isEmpty()) {
            phone.setError("Can't be Empty");
            return false;
        }

        if (email.getText().toString().isEmpty()) {
            email.setError("Can't be Empty");
            return false;
        }

        if (address.getText().toString().isEmpty()) {
            address.setError("Can't be Empty");
            return false;
        }

        return flag;
    }

}