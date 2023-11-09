package com.example.productos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.example.productos.databinding.ActivityProductoBinding;
import com.example.productos.pojos.Product;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ProductActivity extends AppCompatActivity {

        private ActivityProductoBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_producto);
        binding = ActivityProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Product product = getIntent().getParcelableExtra("product");

        binding.productIdEdit.setText(""+product.getId());
        binding.productNameEdit.setText(product.getName());
        binding.productPriceEdit.setText(""+product.getPrice());
        binding.productStockEdit.setText(""+product.getStock());
        byte[] encodeByte = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        binding.productImage.setImageBitmap(bitmap);

    }
}