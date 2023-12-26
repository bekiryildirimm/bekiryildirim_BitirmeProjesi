package com.example.ag_analizi;

import android.graphics.drawable.Drawable;

public class PingItems {
    int pingItemIcon;
    int SiraNo;
    String IpAdresi;
   long sure;
   String HostAdi;

    public PingItems(int pingItemIcon, int siraNo, String ipAdresi, long sure, String hostAdi) {

        this.pingItemIcon = pingItemIcon;
        SiraNo = siraNo;
        IpAdresi = ipAdresi;
        this.sure = sure;
        HostAdi = hostAdi;
    }

    public int getPingItemIcon() {
        return pingItemIcon;
    }

    public int getSiraNo() {
        return SiraNo;
    }

    public String getIpAdresi() {
        return IpAdresi;
    }

    public long getSure() {
        return sure;
    }

    public String getHostAdi() {
        return HostAdi;
    }
}

class PingComparator implements java.util.Comparator<PingItems>{

    @Override
    public int compare(PingItems o1, PingItems o2) {
        return o2.getSiraNo()-o1.getSiraNo();
    }
}
