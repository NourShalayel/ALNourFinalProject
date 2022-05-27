package com.example.alnour;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder> {
    Activity activity;
    ArrayList<Person> personList;

    public PersonAdapter(Activity activity, ArrayList<Person> personList) {
        this.activity = activity;
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.activity_recycler_des, parent, false);
        return new PersonAdapter.PersonHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        int i = position;

        holder.txtname.setText(personList.get(position).getName());
        holder.txtphonenumber.setText(personList.get(position).getCall());
        holder.txtemail.setText(personList.get(position).getEmail());
        holder.txtaddress.setText(personList.get(position).getAddress());



    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class PersonHolder extends RecyclerView.ViewHolder {
        TextView txtname;
        TextView txtphonenumber;
        TextView txtemail;
        TextView txtaddress;

        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.per_name);
            txtphonenumber = itemView.findViewById(R.id.per_phonenumber);
            txtemail = itemView.findViewById(R.id.per_email);
            txtaddress = itemView.findViewById(R.id.per_address);
        }
    }
}
