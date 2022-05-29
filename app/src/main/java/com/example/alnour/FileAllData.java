package com.example.alnour;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileAllData {

    ArrayList<Product> pro_list = new ArrayList<>();
    ArrayList<Person> sup_list = new ArrayList<>();
    ArrayList<Category> cat_list = new ArrayList<>();
    ArrayList<Person> cus_list = new ArrayList<>();
    String productFile;
    private FirebaseStorage storage;
    private DatabaseReference ref;
    private StorageReference sref;
    private FirebaseFirestore db;
Context context ;

//    public void readProductFromDB() {
//
//        pro_list.clear();
//
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference ref = db.getReference("products");
//        Task<DataSnapshot> task = ref.get();
//        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    Iterable<DataSnapshot> data = task.getResult().getChildren();
//
//                    for (DataSnapshot snap : data) {
//                        Product pro = snap.getValue(Product.class);
//                        pro_list.add(pro);
//                        Log.e("ee", "" + pro_list);
//                    }
//
//                    productFile = new Gson().toJson(pro_list);
//                    sref = FirebaseStorage.getInstance().getReference().child("Document");
//                    sref.child(productFile).putBytes(productFile.getBytes()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            Toast.makeText(context , "Success" ,Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                        }
//                    });
//                } else {
//                    String errorMessage = task.getException().getMessage();
////                    Toast.makeText(getActivity(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//
//
//    }


    public void readCustomerFromDB() {

        cus_list.clear();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("customers");
        Task<DataSnapshot> task = ref.get();
        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Iterable<DataSnapshot> data = task.getResult().getChildren();
                    for (DataSnapshot snap : data) {
                        Person cus = snap.getValue(Person.class);
                        cus_list.add(cus);
                        Log.e("ee", "" + cus_list);
                    }


                } else {
                    String errorMessage = task.getException().getMessage();
//                    Toast.makeText(getActivity(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void readSupplierFromDB() {

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
                        sup_list.add(sup);
                        Log.e("ee", "" + sup_list);
                    }

                } else {
                    String errorMessage = task.getException().getMessage();
//                    Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void readCategoryFromDB() {

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
                        cat_list.add(cat);
                        Log.e("ee", "" + cat_list);
                    }


                } else {
                    String errorMessage = task.getException().getMessage();
//                    Toast.makeText(getActivity(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}



