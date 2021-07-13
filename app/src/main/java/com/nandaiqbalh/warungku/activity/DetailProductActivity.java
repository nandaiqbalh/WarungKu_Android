package com.nandaiqbalh.warungku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.model.Product;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailProductActivity extends AppCompatActivity {
    TextView tvNama, tvHarga, tvDeskripsi;
    ImageView ivGambar;

    // intent getExtra
    Gson gson = new Gson();
    SharedPreferences sp;
    Product product;

    // Toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        init();
        getInfo();
    }

    private void getInfo(){
        String dataProduct = getIntent().getStringExtra("extra");
        product = gson.fromJson(dataProduct, Product.class);  // cast dari bentuk String ke bentuk Object Produk

        // set value
        tvNama.setText(product.getNama());
        tvHarga.setText(NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(Integer.valueOf(product.getHarga())));
        tvDeskripsi.setText(product.getDeskripsi());

        // Gambar
        String imageURL = "http://192.168.160.108/warungKu/public/storage/product/" + product.getGambar();
        Picasso.get()
                .load(imageURL)
                .placeholder(R.drawable.iv_loading)
                .error(R.drawable.iv_loading)
                .resize(400, 400)
                .into(ivGambar);

        // set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(product.getNama());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void init(){
        tvNama = (TextView) findViewById(R.id.tv_nama);
        tvHarga = (TextView) findViewById(R.id.tv_harga);
        tvDeskripsi = (TextView) findViewById(R.id.tv_deskripsi);
        ivGambar = (ImageView) findViewById(R.id.gambar);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

    }
}