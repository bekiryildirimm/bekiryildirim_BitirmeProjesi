package com.example.ag_analizi;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;

public class VeriKullanimiItems {
   // int veriIconImageView;
    Drawable veriIconImageView;
    int veriProgressbar;
    String veriUygulamaAdi;
    String veriKullanim;

    public VeriKullanimiItems(Drawable veriIconImageView, int veriProgressbar, String veriUygulamaAdi, String veriKullanim) {
        this.veriIconImageView = veriIconImageView;
        this.veriProgressbar = veriProgressbar;
        this.veriUygulamaAdi = veriUygulamaAdi;
        this.veriKullanim = veriKullanim;
    }

    public Drawable getVeriIconImageView() {
        return veriIconImageView;
    }
/*public int getVeriIconImageView() {
        return veriIconImageView;
    }*/

    public int getVeriProgressbar() {
        return veriProgressbar;
    }

    public String getVeriUygulamaAdi() {
        return veriUygulamaAdi;
    }

    public String getVeriKullanim() {
        return veriKullanim;
    }
}
