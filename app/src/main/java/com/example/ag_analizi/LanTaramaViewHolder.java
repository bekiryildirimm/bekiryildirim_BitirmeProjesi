package com.example.ag_analizi;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LanTaramaViewHolder extends RecyclerView.ViewHolder {
    TextView lanCihazAdiTv, lanIpAdresTv,lanMacAdresTv,lanSaticiAdiTv;

    public LanTaramaViewHolder(@NonNull View itemView) {
        super(itemView);
        lanCihazAdiTv=itemView.findViewById(R.id.lanCihazAdiTv);
        lanIpAdresTv=itemView.findViewById(R.id.lanIpAdresTv);
        lanMacAdresTv=itemView.findViewById(R.id.lanMacAdresTv);
        lanSaticiAdiTv=itemView.findViewById(R.id.lanSaticiAdiTv);

    }
}
