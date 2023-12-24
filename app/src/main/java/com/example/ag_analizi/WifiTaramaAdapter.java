package com.example.ag_analizi;

import android.content.Context;
import android.media.RouteListingPreference;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class WifiTaramaAdapter extends RecyclerView.Adapter<WifiTaramaViewHolder> {


    Context context;
    List<wifiSinyalitems> items;

    public WifiTaramaAdapter(List<wifiSinyalitems> items) {
       // this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public WifiTaramaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new WifiTaramaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wifi_tarama,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WifiTaramaViewHolder holder, int position) {


     holder.channel.setText(items.get(position).getChannel());
     holder.sifreleme.setText(items.get(position).getSifreleme());
     holder.bssid.setText(items.get(position).getBssid());
     holder.ssid.setText(items.get(position).getSsid());
     holder.sinyalGucu.setText(items.get(position).getSinyalGucu());
     holder.wps.setText(items.get(position).getWps());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
