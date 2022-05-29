package com.example.alnour;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InvoiceProductFragment extends Fragment {
    RecyclerView rfProductsCart;
    TextView total ;
    public InvoiceProductFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invoice_product, container, false);

        rfProductsCart = v.findViewById(R.id.rfProductsCart);
        total = v.findViewById(R.id.total);

        Bundle bundle = getArguments();
        ArrayList<Product> array_pro = bundle.getParcelableArrayList("ProductCart");

        if (bundle != null) {
            Log.e("eee", "onCreateView: " + array_pro);

        }

        InvoiceProductAdapter adapter = new InvoiceProductAdapter(array_pro , (MainActivity)getActivity() , InvoiceProductFragment.this);

        rfProductsCart.setAdapter(adapter);
        rfProductsCart.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));

        return v;

    }


}