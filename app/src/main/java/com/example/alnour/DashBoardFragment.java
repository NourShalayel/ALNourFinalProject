package com.example.alnour;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DashBoardFragment extends Fragment {


    public DashBoardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dash_board, container, false);
        View c = v.findViewById(R.id.customer_btn);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerFragment customerFrag = new customerFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fcv, customerFrag);
                ft.commit();
            }
        });
        return v;
    }
}