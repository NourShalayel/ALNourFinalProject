package com.example.alnour;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DiscountFragment extends Fragment {

    FloatingActionButton addDiscountbtn ;

    public DiscountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_discount, container, false);
        addDiscountbtn = v.findViewById(R.id.addDiscountbtn);

        addDiscountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext() , AddDiscountActivity.class);
                startActivity(i);
            }
        });
        return v;
    }
}