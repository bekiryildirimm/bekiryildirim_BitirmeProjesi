package com.example.ag_analizi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PingViewHolder extends RecyclerView.ViewHolder {
    ImageView pingTaramaIcon;
    TextView pingSiraNoTv,pingIpTv,pingSureTv,pingHostAdiTv;

    public PingViewHolder(@NonNull View itemView) {
        super(itemView);
        pingTaramaIcon=itemView.findViewById(R.id.pingTaramaIcon);
                pingSiraNoTv=itemView.findViewById(R.id.pingSiraNoTv);
        pingIpTv=itemView.findViewById(R.id.pingIpTv);
                pingSureTv=itemView.findViewById(R.id.pingSureTv);
        pingHostAdiTv=itemView.findViewById(R.id.pingHostAdiTv);
    }
}
