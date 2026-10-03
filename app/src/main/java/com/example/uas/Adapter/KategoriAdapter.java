package com.example.uas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.uas.Activity.ListMakananActivity;
import com.example.uas.Domain.Kategori;
import com.example.uas.Domain.Makanan;
import com.example.uas.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.viewholder> {
    ArrayList<Kategori> items;
    Context context;

    public KategoriAdapter(ArrayList<Kategori> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public KategoriAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_kategori, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriAdapter.viewholder holder, int position) {
        holder.txtTitle.setText(items.get(position).getName());

        switch (position){
            case 0: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_bg);
                break;
            }
            case 1: {
                holder.pic.setBackgroundResource(R.drawable.cat_1_bg);
                break;
            }
            case 2: {
                holder.pic.setBackgroundResource(R.drawable.cat_2_bg);
                break;
            }
            case 3: {
                holder.pic.setBackgroundResource(R.drawable.cat_3_bg);
                break;
            }
            case 4: {
                holder.pic.setBackgroundResource(R.drawable.cat_4_bg);
                break;
            }
            case 5: {
                holder.pic.setBackgroundResource(R.drawable.cat_5_bg);
                break;
            }
            case 6: {
                holder.pic.setBackgroundResource(R.drawable.cat_6_bg);
                break;
            }
            case 7: {
                holder.pic.setBackgroundResource(R.drawable.cat_7_bg);
                break;
            }
        }

        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath()
                , "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListMakananActivity.class);
            intent.putExtra("CategoryId", items.get(position).getId());
            intent.putExtra("CategoryName", items.get(position).getName());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {

        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat);

        }
    }
}
