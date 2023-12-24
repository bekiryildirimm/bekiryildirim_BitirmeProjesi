package com.example.ag_analizi;

public class wifiSinyalitems {

    String channel;
    String   ssid;
    String sinyalGucu;
    String bssid;
    String sifreleme;
    String wps;

    public wifiSinyalitems(String channel, String ssid, String sinyalGucu, String bssid, String sifreleme, String wps) {
        this.channel = channel;
        this.ssid = ssid;
        this.sinyalGucu = sinyalGucu;
        this.bssid = bssid;
        this.sifreleme = sifreleme;
        this.wps = wps;
    }

    public String getChannel() {
        return channel;
    }

    public String getSsid() {
        return ssid;
    }

    public String getSinyalGucu() {
        return sinyalGucu;
    }

    public String getBssid() {
        return bssid;
    }

    public String getSifreleme() {
        return sifreleme;
    }

    public String getWps() {
        return wps;
    }
}
