package com.example.alnour;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomerFragment extends Fragment {

    RecyclerView rfCustomer;

    public CustomerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_customer, container, false);
        View addCustomer = v.findViewById(R.id.addcustomerbtn);

        rfCustomer = v.findViewById(R.id.rfCustomer);

//        Intent i = getIntent();
//        long id = i.getLongExtra("id" , 1 );
        PersonAdapter cusAdapter = new PersonAdapter(getActivity(), readCustomerFromDB());
        rfCustomer.setAdapter(cusAdapter);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rfCustomer.setLayoutManager(lm);

        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddCustomerActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    public ArrayList<Person> readCustomerFromDB() {
        ArrayList<Person> cus_list = new ArrayList<>() ;

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
                        Log.e("ee" , ""+cus_list);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(getActivity(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return cus_list;
    }

}