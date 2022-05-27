package com.example.alnour;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductFragment extends Fragment {


    RecyclerView rfProduct;
    ArrayList<Product> pro_list = new ArrayList<>() ;
    FloatingActionButton addProduct ;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_product, container, false);

        View addProduct = v.findViewById(R.id.addproductbtn);

        rfProduct = v.findViewById(R.id.rfProducts);


        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , AddProductActivity.class);
                startActivity(intent);
            }
        });


        return  v ;
    }

    @Override
    public void onResume() {
        super.onResume();
        readProductFromDB();
    }


    public void readProductFromDB() {

        pro_list.clear();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("products");
        Task<DataSnapshot> task = ref.get();
        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Iterable<DataSnapshot> data = task.getResult().getChildren();
                    for (DataSnapshot snap : data) {
                        Product pro = snap.getValue(Product.class);
                        pro_list.add(pro);
                        Log.e("ee" , ""+pro_list);
                    }

                    ProductAdapter adapter = new ProductAdapter(pro_list);

                    rfProduct.setAdapter(adapter);
                    rfProduct.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));
                    Toast.makeText(getActivity(), "" + (MainActivity)getActivity() , Toast.LENGTH_SHORT).show();

                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(getActivity(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}