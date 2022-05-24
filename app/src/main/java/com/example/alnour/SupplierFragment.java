package com.example.alnour;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupplierFragment extends Fragment {


    public SupplierFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_supplier, container, false);

        View addSupplier = v.findViewById(R.id.addsupplierbtn);

        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , AddSupplierActivity.class);
                startActivity(intent);
            }
        });



        return v ;
    }
}