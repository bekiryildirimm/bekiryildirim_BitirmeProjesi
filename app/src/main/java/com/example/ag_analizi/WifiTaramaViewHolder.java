package com.example.ag_analizi;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WifiTaramaViewHolder extends RecyclerView.ViewHolder {
 TextView channel, ssid,sinyalGucu,bssid,sifreleme,wps;
    public WifiTaramaViewHolder(@NonNull View itemView) {
        super(itemView);

        channel=itemView.findViewById(R.id.channelView);
        ssid=itemView.findViewById(R.id.ssidView);
        sinyalGucu=itemView.findViewById(R.id.strengthView);
        bssid=itemView.findViewById(R.id.bssidView);
        sifreleme=itemView.findViewById(R.id.encryptionView);
        wps=itemView.findViewById(R.id.othersView);
    }
}
