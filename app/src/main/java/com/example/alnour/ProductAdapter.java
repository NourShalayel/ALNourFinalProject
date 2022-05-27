package com.example.alnour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    ArrayList<Product> proList;

    public ProductAdapter(ArrayList<Product> proList) {
        this.proList = proList;
    }


    @NonNull
    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_product_design, parent, false);
        return new ProductAdapter.ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductHolder holder, int position) {
        int i = position;

        holder.product_name.setText(proList.get(position).getName());
        holder.product_code.setText(proList.get(position).getCode()+"");
        holder.product_price.setText(proList.get(position).getPrice()+"");
        holder.product_unit.setText(proList.get(position).getUnit()+"");
        holder.product_desc.setText(proList.get(position).getDescription());
        holder.product_cat.setText(proList.get(position).getCat_id());
        holder.product_supplier.setText(proList.get(position).getSup_id());

        if (!proList.get(position).getProImg().isEmpty())
            Glide.with(holder.itemView).load(proList.get(position).getProImg()).into(holder.product_img);
    }


    @Override
    public int getItemCount() {
        return proList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        ImageView product_img ;
        TextView product_name;
        TextView product_code;
        TextView product_price;
        TextView product_unit;
        TextView product_cat;
        TextView product_supplier;
        TextView product_desc;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            product_img = itemView.findViewById(R.id.product_img);
            product_name = itemView.findViewById(R.id.product_name);
            product_code = itemView.findViewById(R.id.product_code);
            product_price = itemView.findViewById(R.id.product_price);
            product_unit = itemView.findViewById(R.id.product_unit);
            product_cat = itemView.findViewById(R.id.product_cat);
            product_supplier = itemView.findViewById(R.id.product_supplier);
            product_desc = itemView.findViewById(R.id.product_desc);
        }
    }
}

