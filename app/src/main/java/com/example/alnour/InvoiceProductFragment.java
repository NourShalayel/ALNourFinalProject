package com.example.alnour;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class InvoiceProductFragment extends Fragment {

    Spinner cus_spinner;
    int cus_position;
    ArrayList<String> cusNamelist = new ArrayList<>();
    private FirebaseDatabase db ;
    private DatabaseReference ref ;
    Button checkoutbtn;
    RecyclerView rfProductsCart;
    TextView total;
    String selectedItem;

    public InvoiceProductFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invoice_product, container, false);

        rfProductsCart = v.findViewById(R.id.rfProductsCart);
        total = v.findViewById(R.id.total);
        cus_spinner = v.findViewById(R.id.selectCustomer);
        checkoutbtn = v.findViewById(R.id.checkoutbtn);

        Bundle bundle = getArguments();
        ArrayList<Product> array_pro = bundle.getParcelableArrayList("ProductCart");

        if (bundle != null) {
            Log.e("eee", "onCreateView: " + array_pro);

        }

        InvoiceProductAdapter adapter = new InvoiceProductAdapter(array_pro, (MainActivity) getActivity(), InvoiceProductFragment.this);

        rfProductsCart.setAdapter(adapter);
        rfProductsCart.setLayoutManager(new LinearLayoutManager((MainActivity) getActivity()));

        cus_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                cus_position = position;
                Log.e("e", "yessssssssssss" + "  " + selectedItem);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cus_position > 0) {
                    Invoice inv = new Invoice(selectedItem, adapter.total, adapter.product_List);
                    addInvoiceToDB(inv);

                } else {
                    Toast.makeText(getContext(), "choice customer !!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;

    }



    @Override
    public void onResume() {
        super.onResume();
        readCustomerNames();
    }

    public void readCustomerNames() {

        cusNamelist.clear();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("customers");
        Task<DataSnapshot> task = ref.get();
        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Iterable<DataSnapshot> data = task.getResult().getChildren();
                    cusNamelist.add("Select Customer");
                    for (DataSnapshot snap : data) {
                        Person sup = snap.getValue(Person.class);
                        cusNamelist.add(sup.getName());
                        Log.d("d", "" + sup);
                    }
                    if (cusNamelist != null && cusNamelist.size() != 0) {
                        ArrayAdapter<String> sup_adapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cusNamelist);
                        cus_spinner.setAdapter(sup_adapter);
                        sup_adapter.notifyDataSetChanged();
                    }

                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addInvoiceToDB(Invoice inv) {
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("invoices");
        String id = ref.push().getKey();

        ref.child(id).setValue(inv).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "checked out successfully", Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStackImmediate();
                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(getContext(), "Fail " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
