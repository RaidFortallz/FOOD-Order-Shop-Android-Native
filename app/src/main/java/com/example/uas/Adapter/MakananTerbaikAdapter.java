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

public class MakananTerbaikAdapter extends RecyclerView.Adapter<MakananTerbaikAdapter.viewholder> {
    ArrayList<Makanan> items;
    Context context;

    public MakananTerbaikAdapter(ArrayList<Makanan> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MakananTerbaikAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananTerbaikAdapter.viewholder holder, int position) {
        holder.txtTitle.setText(items.get(position).getTitle());
        NumberFormat format = NumberFormat.getNumberInstance(new Locale("id", "ID"));
        String harga = format.format(items.get(position).getPrice());
        holder.txtPrice.setText("Rp" + harga);
        holder.txtTime.setText(items.get(position).getTimeValue() + " min");
        holder.txtStar.setText("" + items.get(position).getStar());

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
        TextView txtTitle, txtPrice, txtStar, txtTime;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.titleTxt);
            txtPrice = itemView.findViewById(R.id.priceTxt);
            txtStar = itemView.findViewById(R.id.starTxt);
            txtTime = itemView.findViewById(R.id.timeTxt);
            pic = itemView.findViewById(R.id.pic);

        }
    }
}
