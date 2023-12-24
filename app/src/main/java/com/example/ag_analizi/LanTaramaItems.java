package com.example.ag_analizi;

public class LanTaramaItems {
    String lanCihazAdi;
    String lanMacAdres;
    String lanIpAdres;
    String lanSaticiAdi;

    public LanTaramaItems(String lanCihazAdi, String lanMacAdres, String lanIpAdres, String lanSaticiAdi) {
        this.lanCihazAdi = lanCihazAdi;
        this.lanMacAdres = lanMacAdres;
        this.lanIpAdres = lanIpAdres;
        this.lanSaticiAdi = lanSaticiAdi;
    }

    public String getLanCihazAdi() {
        return lanCihazAdi;
    }

    public String getLanMacAdres() {
        return lanMacAdres;
    }

    public String getLanIpAdres() {
        return lanIpAdres;
    }

    public String getLanSaticiAdi() {
        return lanSaticiAdi;
    }
}
