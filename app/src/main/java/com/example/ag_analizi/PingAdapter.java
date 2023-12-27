package com.example.ag_analizi;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PingAdapter extends RecyclerView.Adapter<PingViewHolder> {

    List<PingItems> items;

    public PingAdapter(List<PingItems> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ping,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PingViewHolder holder, int position) {
    holder.pingHostAdiTv.setText(items.get(position).getHostAdi());
        holder.pingIpTv.setText(items.get(position).getIpAdresi());
        holder.pingSiraNoTv.setText(Integer.toString(items.get(position).getSiraNo()));
        holder.pingSureTv.setText(items.get(position).getSure());
        holder.pingTaramaIcon.setImageResource(items.get(position).getPingItemIcon());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
