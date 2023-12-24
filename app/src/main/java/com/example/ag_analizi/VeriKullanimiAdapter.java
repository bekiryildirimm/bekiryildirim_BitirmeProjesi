package com.example.ag_analizi;

import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VeriKullanimiAdapter extends RecyclerView.Adapter<VeriKullanimiViewHolder> {


    List<VeriKullanimiItems> items;

    public VeriKullanimiAdapter(List<VeriKullanimiItems> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VeriKullanimiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VeriKullanimiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veri_kullanimi,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VeriKullanimiViewHolder holder, int position) {
     holder.veriKullanim.setText(items.get(position).getVeriKullanim());
     holder.veriUygulamaAdi.setText(items.get(position).getVeriUygulamaAdi());
     holder.veriProgressBar.setProgress(items.get(position).getVeriProgressbar());
     //holder.veriIconImageView.setImageBitmap(items.get(position).getVeriIconImageView());
      //  holder.veriIconImageView.setImageResource(items.get(position).getVeriIconImageView());
        holder.veriIconImageView.setImageDrawable(items.get(position).getVeriIconImageView());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
