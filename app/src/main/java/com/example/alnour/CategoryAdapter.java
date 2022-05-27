package com.example.alnour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    ArrayList<Category> catList;

    public CategoryAdapter(ArrayList<Category> catList) {
        this.catList = catList;
    }


    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_recycler_category, parent, false);
        return new CategoryAdapter.CategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        int i = position;

        holder.txtname.setText(catList.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return catList.size();
    }


    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView txtname;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.catNameDesign);

        }
    }
}

