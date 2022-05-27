package com.example.alnour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

public class AddProductActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1021;

    private TextInputEditText pro_name, pro_code, pro_price, pro_unit, pro_description;
    String cat_id, sup_id;
    ArrayList<String> cat_items;
    ArrayList<String> sup_items;
    ImageView ivImage;
    Uri selectedImage;
    FloatingActionButton selectImg_btn;

    private Button addProductbtn;
    Spinner cat_spinner;
    Spinner sup_spinner;
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private StorageReference sref;
    private FirebaseStorage storage;

    ArrayList<Category> cat_list = new ArrayList<>();
    ArrayList<Person> sup_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_add_product);

        init();

        cat_items = new ArrayList<>();
        sup_items = new ArrayList<>();

        if (cat_items != null && cat_items.size() != 0) {

            ArrayAdapter<String> cat_adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, cat_items);
            cat_spinner.setAdapter(cat_adapter);
            cat_adapter.notifyDataSetChanged();


        }

        if (sup_items != null && sup_items.size() != 0) {

            ArrayAdapter<String> sup_adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, sup_items);
            sup_spinner.setAdapter(sup_adapter);
            sup_adapter.notifyDataSetChanged();
        }

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

        addProductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductToBD();
            }
        });

        selectImg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent().setType("image/*");
                startActivityForResult(i, PICK_IMAGE);
            }
        });

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

    public void init() {
        pro_name = findViewById(R.id.pro_name);
        pro_code = findViewById(R.id.pro_code);
        pro_price = findViewById(R.id.pro_price);
        pro_unit = findViewById(R.id.pro_unit);
        pro_description = findViewById(R.id.pro_description);
        addProductbtn = findViewById(R.id.addProductbtn);
        cat_spinner = findViewById(R.id.selectCategory);
        sup_spinner = findViewById(R.id.selectSupplier);
        ivImage = findViewById(R.id.pro_image);
        selectImg_btn = findViewById(R.id.selectImg_btn);
    }

    private void addProductToBD() {
        String name = pro_name.getText().toString();
        int code = Integer.parseInt(pro_code.getText().toString());
        double price = Double.parseDouble(pro_price.getText().toString());
        int unit = Integer.parseInt(pro_unit.getText().toString());
        String desc = pro_description.getText().toString();

        if (name.isEmpty()) {
            pro_name.setError("name can not be empty !");
        } else if (code < 9999999) {
            pro_code.setError("code must be 8 digits !");
        } else if (price < 0) {
            pro_price.setError("price can not be empty !");
        } else if (unit < 0) {
            pro_unit.setError("units can not be empty !");
        } else if (desc.isEmpty()) {
            pro_name.setError("description can not be empty !");
        } else if (cat_id.isEmpty()) {
            Toast.makeText(AddProductActivity.this, "please choice Category !!", Toast.LENGTH_SHORT);
        } else if (sup_id.isEmpty()) {
            Toast.makeText(AddProductActivity.this, "please choice Supplier !!", Toast.LENGTH_SHORT);
        } else {
            db = FirebaseDatabase.getInstance();
            ref = db.getReference("products");
            String id = ref.push().getKey();

            if (selectedImage != null) {
                storage = FirebaseStorage.getInstance();
                sref = storage.getReference("images/" + id);
                sref.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            sref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        String imageUrl = task.getResult().toString();
                                        Product pro = new Product(id, name, code, price, unit, desc, cat_id, sup_id, imageUrl);

                                        ref.child(id).setValue(pro).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(AddProductActivity.this, "add product successfully", Toast.LENGTH_SHORT).show();
                                                    onBackPressed();
                                                } else {
                                                    String errorMessage = task.getException().getMessage();
                                                    Toast.makeText(AddProductActivity.this, "Fail " + errorMessage, Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        } else {

                        }

                    }
                });


            } else {
                Toast.makeText(AddProductActivity.this, "please choice image", Toast.LENGTH_SHORT);
            }


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Read From DB
        readCategories();
        readSuppliers();
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
                    for (DataSnapshot snap : data) {
                        Category cat = snap.getValue(Category.class);
                        cat_items.add(cat.getName());
                        cat_list.add(cat);
                        Log.d("d", "" + cat);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(AddProductActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
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
                    for (DataSnapshot snap : data) {
                        Person sup = snap.getValue(Person.class);
                        sup_items.add(sup.getName());
                        sup_list.add(sup);
                        Log.d("d", "" + sup);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(AddProductActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}