package com.example.uas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.Adapter.KategoriAdapter;
import com.example.uas.Adapter.MakananTerbaikAdapter;
import com.example.uas.Domain.Harga;
import com.example.uas.Domain.Kategori;
import com.example.uas.Domain.Lokasi;
import com.example.uas.Domain.Makanan;
import com.example.uas.Domain.Waktu;
import com.example.uas.R;
import com.example.uas.databinding.ActivityDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DashboardActivity extends BaseActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        initLocation();
        initTime();
        initPrice();
        initBestFood();
        initCategory();
        setVariable();

    }

    private void setVariable() {
        binding.btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            finish();
        });
        binding.imgSearch.setOnClickListener(v -> {
            String text = binding.searchEdt.getText().toString();
            if (!text.isEmpty()){
                Intent intent = new Intent(DashboardActivity.this, ListMakananActivity.class);
                intent.putExtra("text", text);
                intent.putExtra("isSearch", true);
                startActivity(intent);
            }
        });
    }

    private void initBestFood() {
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Makanan> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Makanan.class));
                    }
                    if (list.size()>0){
                        binding.bestFoodView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        RecyclerView.Adapter adapter = new MakananTerbaikAdapter(list);
                        binding.bestFoodView.setAdapter(adapter);
                    }
                    binding.progressBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarKategori.setVisibility(View.VISIBLE);
        ArrayList<Kategori> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Kategori.class));
                    }
                    if (list.size()>0){
                        binding.kategoriView.setLayoutManager(new GridLayoutManager(DashboardActivity.this, 4));
                        RecyclerView.Adapter adapter = new KategoriAdapter(list);
                        binding.kategoriView.setAdapter(adapter);
                    }
                    binding.progressBarKategori.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initLocation() {
        DatabaseReference myRef = database.getReference("Location");
        ArrayList<Lokasi> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Lokasi.class));
                    }
                    ArrayAdapter<Lokasi> adapter = new ArrayAdapter<>(DashboardActivity.this, R.layout.sp_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initTime() {
        DatabaseReference myRef = database.getReference("Time");
        ArrayList<Waktu> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Waktu.class));
                    }
                    ArrayAdapter<Waktu> adapter = new ArrayAdapter<>(DashboardActivity.this, R.layout.sp_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.timeSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initPrice() {
        DatabaseReference myRef = database.getReference("Price");
        ArrayList<Harga> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Harga.class));
                    }
                    ArrayAdapter<Harga> adapter = new ArrayAdapter<>(DashboardActivity.this, R.layout.sp_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.priceSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}