package com.example.uas.Adapter;

import android.content.Context;
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
import com.example.uas.Domain.Makanan;
import com.example.uas.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MakananListAdapter extends RecyclerView.Adapter<MakananListAdapter.viewholder> {

    ArrayList<Makanan> items;
    Context context;

    public MakananListAdapter(ArrayList<Makanan> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MakananListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_list_makanan, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananListAdapter.viewholder holder, int position) {
        holder.judulTxt.setText(items.get(position).getTitle());
        holder.waktuTxt.setText(items.get(position).getTimeValue() + " min");
        NumberFormat format = NumberFormat.getNumberInstance(new Locale("id", "ID"));
        String harga = format.format(items.get(position).getPrice());
        holder.hargaTxt.setText("Rp" + harga);
        holder.ratingTxt.setText("" + items.get(position).getStar());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView judulTxt, hargaTxt, ratingTxt, waktuTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            judulTxt = itemView.findViewById(R.id.titleTxt);
            hargaTxt = itemView.findViewById(R.id.priceTxt);
            ratingTxt = itemView.findViewById(R.id.rateTxt);
            waktuTxt = itemView.findViewById(R.id.timeTxt);
            pic = itemView.findViewById(R.id.img);
        }
    }
}
