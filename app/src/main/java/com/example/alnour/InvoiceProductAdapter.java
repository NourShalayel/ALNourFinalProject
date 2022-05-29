package com.example.alnour;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InvoiceProductAdapter extends RecyclerView.Adapter<InvoiceProductAdapter.ProductHolder> {
    ArrayList<Product> product_List;
    Invoice invoice;
    Context context;
    InvoiceProductFragment ipv ;
    String cat_name = "";
    double total;

    public InvoiceProductAdapter(ArrayList<Product> product_List, Context context, InvoiceProductFragment ipv) {
        this.product_List = product_List;
        this.ipv = ipv;
        this.context = context;
    }


    @NonNull
    @Override
    public InvoiceProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_product_cartdes, parent, false);


        return new InvoiceProductAdapter.ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceProductAdapter.ProductHolder holder, int position) {
        int i = position;

        String productId = product_List.get(position).getId();
        String name = product_List.get(position).getName();
        String code = product_List.get(position).getCode() + "";
        String price = product_List.get(position).getPrice() + "";


        String image_product = product_List.get(position).getProImg();


        holder.product_name.setText(name);
        holder.product_code.setText(code);
        holder.product_price.setText(price);
        product_List.get(i).setUnit(1);
        holder.unit_product.setText(product_List.get(i).getUnit() + "");

        if (!product_List.get(position).getProImg().isEmpty()) {
            Glide.with(holder.itemView).load(image_product).into(holder.product_img);
        }
        UpdateTotal();
        holder.decreasNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product_List.get(i).getUnit() != 1) {
                    product_List.get(i).setUnit(product_List.get(i).getUnit() - 1);
                    holder.unit_product.setText(product_List.get(i).getUnit() + "");
                    UpdateTotal();

                } else {
                    Toast.makeText(context, "Unit Can not be less than 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.increasNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_List.get(i).setUnit(product_List.get(i).getUnit() + 1);
                holder.unit_product.setText(product_List.get(i).getUnit() + "");
                UpdateTotal();
            }
        });

        holder.delete_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_List.remove(product_List.get(i));
                notifyDataSetChanged();
            }
        });

        Log.e("tt", product_List.get(i).getUnit() * product_List.get(position).getPrice() + "");
        total += product_List.get(i).getUnit() * product_List.get(position).getPrice();
        Log.e("tt", total + "");

    }

    private void UpdateTotal() {
        double sum = 0;
        for(int i = 0 ; i< product_List.size() ; i++){
            Log.e("tt", sum + "sum");
            sum += product_List.get(i).getUnit() *  product_List.get(i).getPrice();
        }
        ipv.total.setText(sum+"") ;
    }

    @Override
    public int getItemCount() {
        return product_List.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        ImageView product_img;
        TextView product_name;
        TextView product_code;
        TextView product_price;
        TextView unit_product;
        Button delete_product;
        Button increasNumber;
        Button decreasNumber;


        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            product_img = itemView.findViewById(R.id.product_img);
            product_name = itemView.findViewById(R.id.product_name);
            product_code = itemView.findViewById(R.id.product_code);
            product_price = itemView.findViewById(R.id.product_price);
            unit_product = itemView.findViewById(R.id.unit_product);
            delete_product = itemView.findViewById(R.id.delete_product);
            increasNumber = itemView.findViewById(R.id.increasNumber);
            decreasNumber = itemView.findViewById(R.id.decreasNumber);

        }


    }

}

