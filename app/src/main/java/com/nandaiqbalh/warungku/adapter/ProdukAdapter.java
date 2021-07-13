package com.nandaiqbalh.warungku.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nandaiqbalh.warungku.MainActivity;
import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.activity.DetailProductActivity;
import com.nandaiqbalh.warungku.activity.LoginActivity;
import com.nandaiqbalh.warungku.model.Product;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.myViewHolder> {

    ArrayList<Product> dataHolder;
    Activity activity;

    // constructor
    public ProdukAdapter(Activity activity, ArrayList<Product> dataHolder) {
        this.dataHolder = dataHolder;
        this.activity = activity;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_produk, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.nama.setText(dataHolder.get(position).getNama());
        holder.harga.setText(NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(Integer.valueOf(dataHolder.get(position).getHarga())));
        // Gambar
        String imageURL = "http://192.168.160.108/warungKu/public/storage/product/" + dataHolder.get(position).getGambar();
        Picasso.get()
                .load(imageURL)
                .placeholder(R.drawable.icon)
                .error(R.drawable.icon)
                .into(holder.gambarProduk);

        holder.layoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailProductActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView gambarProduk;
        TextView nama, harga;
        View layoutProduct;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            gambarProduk = itemView.findViewById(R.id.img_produk);
            nama = itemView.findViewById(R.id.tv_nama);
            harga = itemView.findViewById(R.id.tv_harga);
            layoutProduct = itemView.findViewById(R.id.layout_product);
        }
    }
}
