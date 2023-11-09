package com.example.productos.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productos.R;
import com.example.productos.databinding.ItemProductBinding;
import com.example.productos.pojos.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private ArrayList<Product> products = new ArrayList<>();

    public ProductAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemProductBinding binding = ItemProductBinding.inflate(inflater, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ProductAdapter.ProductViewHolder){
            ProductAdapter.ProductViewHolder holder = (ProductAdapter.ProductViewHolder) viewHolder;
            Product product = products.get(position);
            holder.itemView.setTag(product);
            byte[] encodeByte = Base64.decode(product.getImage(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

            holder.binding.itemImage.setImageBitmap(bitmap);
            holder.binding.itemIdTxt.setText(""+product.getId());
            holder.binding.itemNameTxt.setText(product.getName());
            holder.binding.itemPriceTxt.setText(""+product.getPrice());
            holder.binding.itemStockTxt.setText(""+product.getStock());
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void getUpdate(ArrayList<Product> products){
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();

    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        ItemProductBinding binding;

        public ProductViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
