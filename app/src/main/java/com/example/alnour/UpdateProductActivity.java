package com.example.alnour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UpdateProductActivity extends AppCompatActivity {
    private TextInputEditText pro_name, pro_code, pro_price, pro_unit, pro_description;
    Intent intent;
    String id;
    String cat_id, sup_id;
    ArrayList<String> cat_items = new ArrayList<>();
    ArrayList<String> sup_items = new ArrayList<>();


    ArrayList<Category> cat_list = new ArrayList<>();
    ArrayList<Person> sup_list = new ArrayList<>();
    Button updateProductbtn;
    Spinner cat_spinner;
    Spinner sup_spinner;
    ImageView ivImage;
    Uri selectedImage ;
    FloatingActionButton selectImg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        init();

        Bundle b = getIntent().getExtras();

        if (b != null) {
            Object cus = b.getSerializable("product");

            id = b.getString("id");
            pro_name.setText(b.getString("name"));
            pro_code.setText(b.getString("code"));
            pro_price.setText(b.getString("price"));
            pro_unit.setText(b.getString("unit"));
            pro_description.setText(b.getString("description"));
        } else {
            pro_name.setText("");
            pro_code.setText("");
            pro_price.setText("");
            pro_unit.setText("");
            pro_description.setText("");
        }
        updateProductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputValidation()) {
                    if (UpdateProduct()) {
                        finish();
                    }
                }
            }
        });

        cat_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                cat_id = getIdByCatName(selectedItem);
                Log.e("e", "yessssssssssss" + cat_id + "  " + selectedItem);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sup_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                sup_id = getIdBySupName(selectedItem);
                Log.e("e", "yessssssssssss" + sup_id + "  " + selectedItem);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        readCategories();
        readSuppliers();
    }
    public boolean UpdateProduct(){
        DatabaseReference refCus = FirebaseDatabase.getInstance().getReference("products").child(id);

        String name = pro_name.getText().toString();
        int code = Integer.parseInt(pro_code.getText().toString());
        double price = Double.parseDouble(pro_price.getText().toString());
        int unit = Integer.parseInt(pro_unit.getText().toString());
        String description = pro_description.getText().toString();
        String imageUrl = "";

        Product pro = new Product(id, name, code, price, unit, description, cat_id, sup_id, imageUrl);
        refCus.setValue(pro);
        Toast.makeText(this, "Updated Successfully " , Toast.LENGTH_SHORT).show();

        return  true ;
    }

    public void readCategories() {

        cat_list.clear();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("categories");
        Task<DataSnapshot> task = ref.get();
        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Iterable<DataSnapshot> data = task.getResult().getChildren();
                    cat_items.add("Select Category");
                    for (DataSnapshot snap : data) {
                        Category cat = snap.getValue(Category.class);
                        cat_items.add(cat.getName());
                        cat_list.add(cat);
                        Log.d("d", "" + cat);

                        ArrayAdapter<String> cat_adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cat_items);
                        cat_spinner.setAdapter(cat_adapter);
                        cat_adapter.notifyDataSetChanged();
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(UpdateProductActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void readSuppliers() {

        sup_list.clear();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("suppliers");
        Task<DataSnapshot> task = ref.get();
        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Iterable<DataSnapshot> data = task.getResult().getChildren();
                    sup_items.add("Select Supplier");
                    for (DataSnapshot snap : data) {
                        Person sup = snap.getValue(Person.class);
                        sup_items.add(sup.getName());
                        sup_list.add(sup);
                        Log.d("d", "" + sup);
                    }
                    if (sup_items != null && sup_items.size() != 0) {
                        ArrayAdapter<String> sup_adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sup_items);
                        sup_spinner.setAdapter(sup_adapter);
                        sup_adapter.notifyDataSetChanged();
                    }

                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(UpdateProductActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void init() {
        pro_name = findViewById(R.id.pro_update_name);
        pro_code = findViewById(R.id.pro_update_code);
        pro_price = findViewById(R.id.pro_update_price);
        pro_unit = findViewById(R.id.pro_update_unit);
        pro_description = findViewById(R.id.pro_update_description);
        updateProductbtn = findViewById(R.id.updateProductbtn);
        cat_spinner = findViewById(R.id.select_updateCategory);
        sup_spinner = findViewById(R.id.select_updateSupplier);
        ivImage = findViewById(R.id.pro_update_image);
//        selectImg_btn = findViewById(R.id.pro_update_image);

    }

    private String getIdByCatName(String selectedItem) {
        for (Category c : cat_list) {
            if (selectedItem.equalsIgnoreCase(c.getName())) {
                return c.getId();
            }
        }
        return null;
    }

    private String getIdBySupName(String selectedItem) {
        for (Person p : sup_list) {
            if (selectedItem.equalsIgnoreCase(p.getName())) {
                return p.getId();
            }
        }
        return null;

    }

    public Boolean inputValidation() {
        boolean flag = true;

        if (pro_name.getText().toString().isEmpty()) {
            pro_name.setError("Can't be Empty");
            return false;
        }

        if (pro_code.getText().toString().isEmpty()) {
            pro_code.setError("Can't be Empty");
            return false;
        }

        if (pro_price.getText().toString().isEmpty()) {
            pro_price.setError("Can't be Empty");
            return false;
        }

        if (pro_unit.getText().toString().isEmpty()) {
            pro_unit.setError("Can't be Empty");
            return false;
        }

        if (pro_description.getText().toString().isEmpty()) {
            pro_description.setError("Can't be Empty");
            return false;
        }

        if (cat_id == null) {
            Toast.makeText(this, "choose category !!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (sup_id == null) {
            Toast.makeText(this, "choose supplier !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return flag;
    }
}