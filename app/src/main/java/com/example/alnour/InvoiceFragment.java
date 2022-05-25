package com.example.alnour;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InvoiceFragment extends Fragment {


    public InvoiceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_invoice, container, false);

        View cartProduct = v.findViewById(R.id.cart);

        cartProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InvoiceProductFragment invoiceProductFrag = new InvoiceProductFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fcv, invoiceProductFrag);
                ft.commit();
            }
        });

        return v ;
    }
}