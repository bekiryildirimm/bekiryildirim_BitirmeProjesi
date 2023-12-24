package com.example.ag_analizi;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VeriKullanimiViewHolder extends RecyclerView.ViewHolder {
TextView veriUygulamaAdi,veriKullanim;
ImageView veriIconImageView;
ProgressBar veriProgressBar;

    public VeriKullanimiViewHolder(@NonNull View itemView) {
        super(itemView);
        veriUygulamaAdi=itemView.findViewById(R.id.itemVeriUygulamaAdiTv);
        veriKullanim=itemView.findViewById(R.id.itemVeriKullanimTv);
        veriIconImageView=itemView.findViewById(R.id.itemVeriUygulamaicon);
        veriProgressBar=itemView.findViewById(R.id.itemVeriprogress);
    }
}
