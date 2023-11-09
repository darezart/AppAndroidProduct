package com.example.productos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.productos.adapters.ProductAdapter;
import com.example.productos.databinding.ActivityListProductBinding;
import com.example.productos.pojos.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListProductActivity extends AppCompatActivity {

    private ActivityListProductBinding binding;

    private ArrayList<Product> products;

    private ProductAdapter adapter;

    private RequestQueue requestQueue;

    private String url = "http://192.168.1.75:8040/products";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_product);
        binding = ActivityListProductBinding.inflate(getLayoutInflater(), null, false);
        setContentView(binding.getRoot());

        adapter = new ProductAdapter(this);
        binding.actListProductRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.actListProductRecycler.setHasFixedSize(false);
        binding.actListProductRecycler.setAdapter(adapter);

        this.requestQueue = Volley.newRequestQueue(this);
        queryAll();
    }

    private void queryAll(){
        products = new ArrayList<>();
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONObject respuestaJSON = new JSONObject(response);
                            JSONArray jsonarray = new JSONArray(response);
                            for (int i = 0; i < jsonarray.length(); i++){
                                products.add(new Product(jsonarray.getJSONObject(i).getInt("id"),
                                        jsonarray.getJSONObject(i).getString("name"),
                                        jsonarray.getJSONObject(i).getInt("price"),
                                        jsonarray.getJSONObject(i).getInt("stock"),
                                        jsonarray.getJSONObject(i).getString("image")));
                            }
                            adapter.getUpdate(products);
                            //String id = jsonarray.getJSONObject(0).getString("id");
                            System.out.println("prueba de obtener datos ");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error al consultar productos");
            }
        });
        requestQueue.add(request);
    }
}