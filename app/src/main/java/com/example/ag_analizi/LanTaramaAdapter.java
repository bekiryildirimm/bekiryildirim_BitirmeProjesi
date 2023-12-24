package com.example.ag_analizi;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LanTaramaAdapter extends RecyclerView.Adapter<LanTaramaViewHolder> {

    List<LanTaramaItems> items;

    public LanTaramaAdapter(List<LanTaramaItems> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public LanTaramaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LanTaramaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lan_tarama_sonuc,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LanTaramaViewHolder holder, int position) {
   holder.lanCihazAdiTv.setText(items.get(position).getLanCihazAdi());
   holder.lanIpAdresTv.setText(items.get(position).getLanIpAdres());
   holder.lanMacAdresTv.setText(items.get(position).getLanMacAdres());
   holder.lanSaticiAdiTv.setText(items.get(position).getLanSaticiAdi());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
