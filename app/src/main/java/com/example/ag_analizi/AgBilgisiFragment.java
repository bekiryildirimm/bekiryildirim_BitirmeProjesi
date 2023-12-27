package com.example.ag_analizi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.RouteInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.telephony.CellIdentity;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgBilgisiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgBilgisiFragment extends Fragment {
   public static String AgSsid;

    WifiManager wifiManager;
    Stream<String> arpre;
    WifiInfo wifiInfo;
    TelephonyManager telephonyManager;
    ConnectivityManager connectivityManager;
    NetworkCapabilities networkCapabilities;
    LinkProperties linkProperties;
    LinkProperties lp;
    List<RouteInfo> routes;
    Network[] networks;
    TextView baglantiTuruTv, hariciIpTv, hariciIpv6, ipadresTv, subnetMaskTv, gatewayIpTv, dnsServerIpTv, ipv6AdresiTv, gatewayIpv6Tv, dnsServerIpv6Tv, etkinTv, baglantiDurumuTv, dhcpKiraTv, ssidTv, bssidTv, saticiTv, kanalTv, standartTv, hizTv, maxHizTv, wifiSignalTv, veriDurumTv, veriAktiviteTv, veriDolasimTv, simDurumTv, simOperatorTv, simMccTv, agTipiTv, telefonTipiTv, mobilSinyalTv;
    ImageView etkinIcon, baglantiTuruIcon, veriDurumIcon, veriAktiviteIcon, veriDolasimIcon, simDurumIcon;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgBilgisiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgBilgisiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgBilgisiFragment newInstance(String param1, String param2) {
        AgBilgisiFragment fragment = new AgBilgisiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        linkProperties = connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork());
        telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        networks = connectivityManager.getAllNetworks();

    }

    @Override
    public void onResume() {
        super.onResume();
/*String[] ipadresleri=formatCihazIpAddresses(linkProperties);
ipadresTv.setText(ipadresleri[0]);
ipv6AdresiTv.setText(ipadresleri[1]);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_ag_bilgisi, container, false);
        View view = inflater.inflate(R.layout.fragment_ag_bilgisi, container, false);

        baglantiTuruTv = view.findViewById(R.id.baglantiTuruTv);
        hariciIpTv = view.findViewById(R.id.HariciIpTv);
        hariciIpv6 = view.findViewById(R.id.HariciIPv6Tv);
        ipadresTv = view.findViewById(R.id.ipAdresTv);
        subnetMaskTv = view.findViewById(R.id.subnetMaskTv);
        gatewayIpTv = view.findViewById(R.id.gatewayIpTv);
        dnsServerIpTv = view.findViewById(R.id.dnsServerIpTv);
        ipv6AdresiTv = view.findViewById(R.id.ipv6AdresiTv);
        gatewayIpv6Tv = view.findViewById(R.id.gatewayIpv6Tv);
        dnsServerIpv6Tv = view.findViewById(R.id.dnsServerIpv6Tv);
        etkinTv = view.findViewById(R.id.etkinTv);
        baglantiDurumuTv = view.findViewById(R.id.baglantiDurumuTv);
        dhcpKiraTv = view.findViewById(R.id.dhcpKiraTv);
        ssidTv = view.findViewById(R.id.ssidTv);
        bssidTv = view.findViewById(R.id.bssidTv);
        saticiTv = view.findViewById(R.id.saticiTv);
        kanalTv = view.findViewById(R.id.kanalTv);
        standartTv = view.findViewById(R.id.standartTv);
        hizTv = view.findViewById(R.id.hizTv);
        maxHizTv = view.findViewById(R.id.maxHizTv);
        wifiSignalTv = view.findViewById(R.id.wifiSignalTv);
        veriDurumTv = view.findViewById(R.id.veriDurumTv);
        veriAktiviteTv = view.findViewById(R.id.veriAktiviteTv);
        veriDolasimTv = view.findViewById(R.id.veriDolasimTv);
        simDurumTv = view.findViewById(R.id.simDurumTv);
        simOperatorTv = view.findViewById(R.id.simOperatorTv);
        simMccTv = view.findViewById(R.id.simMccTv);
        agTipiTv = view.findViewById(R.id.agTipiTv);
        telefonTipiTv = view.findViewById(R.id.telefonTipiTv);
        mobilSinyalTv = view.findViewById(R.id.mobilSinyalTv);
        etkinIcon = view.findViewById(R.id.etkinIcon);
        baglantiTuruIcon = view.findViewById(R.id.baglantiTuruIcon);
        veriDurumIcon = view.findViewById(R.id.veriDurumIcon);
        veriAktiviteIcon = view.findViewById(R.id.veriAktiviteIcon);
        veriDolasimIcon = view.findViewById(R.id.veriDolasimIcon);
        simDurumIcon = view.findViewById(R.id.simDurumIcon);
        baglantiTuruTv.setText("merhabaaaaa");
        try {
            String[] ipadresleri = formatCihazIpAddresses(linkProperties);
            ipadresTv.setText(ipadresleri[1]);
            ipv6AdresiTv.setText(ipadresleri[0]);
            dnsServerIpTv.setText(ipadresleri[2]);
            gatewayIpTv.setText(ipadresleri[3]);
            subnetMaskTv.setText(ipadresleri[4]);
        } catch (Exception e) {
            //ipadresTv.setText(e.getMessage());
        }

        try {
            dhcpKiraTv.setText(wifiManager.getDhcpInfo().leaseDuration + "saniye");
        } catch (Exception a) {

        }
        ssidTv.setText(wifiInfo.getSSID());
        AgSsid=wifiInfo.getSSID();
        bssidTv.setText(wifiInfo.getBSSID());
        kanalTv.setText(getChannelFromFrequency(wifiInfo.getFrequency()) + " (" + wifiInfo.getFrequency() + "MHz)");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            standartTv.setText(standartlar[wifiInfo.getWifiStandard()]);
        } else
            standartTv.setText("N/A");
        hizTv.setText(wifiInfo.getLinkSpeed() + " Mbps");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            maxHizTv.setText(wifiInfo.getMaxSupportedRxLinkSpeedMbps() + " / " + wifiInfo.getMaxSupportedTxLinkSpeedMbps() + " Mbps");
        }
        wifiSignalTv.setText(wifiInfo.getRssi() + " dBm");
        //ipadresTv.setText(ipadresleri[0]);
        //ipv6AdresiTv.setText(ipadresleri[1]);

        // telephonyManager.cell

        //simOperatorTv.setText(mobilBilgi(telephonyManager));
        simOperatorTv.setText(telephonyManager.getSimOperatorName());
        simMccTv.setText(telephonyManager.getSimOperator()+" ("+telephonyManager.getSimCountryIso()+")");
        mobilSinyalTv.setText(mobilSinyalGucu(telephonyManager));
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {


             if(telephonyManager.isNetworkRoaming()){
                 veriDolasimTv.setText("Dolaşım Var");
                 veriDolasimIcon.setImageResource(R.drawable.circle_green);

             }
             else
             {
                 veriDolasimTv.setText("Dolaşım Yok");
                 veriDolasimIcon.setImageResource(R.drawable.circle_red);
             }
            }
            else
            {
                veriDolasimTv.setText("Bilinmiyor");
                veriDolasimIcon.setImageResource(R.drawable.circle_red);
            }
            agTipiTv.setText(agTipi[telephonyManager.getDataNetworkType()]);
        }
        catch (SecurityException ser)
        {
agTipiTv.setText("Bilinmiyor");
        }
        veriDurumTv.setText(veriDurumu[telephonyManager.getDataState()]);
        veriAktiviteTv.setText(veriAktivitesi[telephonyManager.getDataActivity()]);
        veriAktiviteIcon.setImageResource(veriaktiviteiconu[telephonyManager.getDataActivity()]);
        simDurumTv.setText(simDurumu[telephonyManager.getSimState()]);
      simDurumIcon.setImageResource(simdurumuiconu[telephonyManager.getSimState()]);
        telefonTipiTv.setText(TelefonTipi[telephonyManager.getPhoneType()]);
        veriDurumIcon.setImageResource(veridurumuiconu[telephonyManager.getDataState()]);
        // etkinTv.setText(executeCommand()+" ");
        //executeCommand();
      /*  InetAddress inet= null;
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            byte[] ip = localhost.getAddress();
            inet=InetAddress.getByAddress(ip);
          //  etkinTv.setText(inet.getHostName());
        }catch (Exception e){
            etkinTv.setText(e.getMessage()+"error1");

        }*/
       /* String srer="";
        for (Network network : networks) {
            lp = connectivityManager.getLinkProperties(network);
            if (lp != null) {
                srer+=formatIpAddresses(lp);
                routes = lp.getRoutes();

               /* for(RouteInfo route:routes)
                {
                    srer+=route.toString();
                  //  etkinTv.setText(route.toString());
                }
            }
        }*/
//etkinTv.setText(srer);
        return view;
    }
    public String getMacAddressForIp(final String ipAddress) {
        try (BufferedReader br = new BufferedReader(new FileReader("/proc/net/arp"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ipAddress)) {
                    final int macStartIndex = line.indexOf(":") - 2;
                    final int macEndPos = macStartIndex + 17;
                    if (macStartIndex >= 0 && macEndPos < line.length()) {
                        return line.substring(macStartIndex, macEndPos);
                    } else {

                        return "mac invalid";
                        //Log.w("MyClass", "Found ip address line, but mac address was invalid.");
                    }
                }
            }
        } catch(Exception e){
            return e.getMessage()+"excep arp";
            //Log.e("MyClass", "Exception reading the arp table.", e);
        }
        return null;
    }
    private boolean executeCommand(){
            System.out.println("executeCommand");
            Runtime runtime = Runtime.getRuntime();
            try
            {
                Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 192.168.1.125");
            int mExitValue = mIpAddrProcess.waitFor();
            System.out.println(" mExitValue "+mExitValue);
            Process process=runtime.exec("ip neigh show");
            process.waitFor();
         BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
        arpre= reader.lines();
       etkinTv.setText(reader.readLine()+"  bakalım");
            if(mExitValue==0){
                return true;
            }else{
                return false;
            }
        }
        catch (InterruptedException ignore)
        {
            ignore.printStackTrace();
            System.out.println(" Exception:"+ignore);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(" Exception:"+e);
        }
        return false;
    }
    private final static ArrayList<Integer> channelsFrequency = new ArrayList<Integer>(
            Arrays.asList(0, 2412, 2417, 2422, 2427, 2432, 2437, 2442, 2447,
                    2452, 2457, 2462, 2467, 2472, 2484));

 //NETWORK_TYPE_EVDO_A, NETWORK_TYPE_EVDO_B, NETWORK_TYPE_1xRTT, NETWORK_TYPE_IDEN, NETWORK_TYPE_LTE, NETWORK_TYPE_EHRPD, NETWORK_TYPE_HSPAP, NETWORK_TYPE_NR
    String[] agTipi={"Bilinmeyen","GPRS","EDGE","UMTS","HSDPA","HSUPA","HSPA","CDMA","EVDO 0","EVDO A","EVDO B","1xRTT","IDEN","LTE","EHRPD","HSPAP","NR"};

    String[] TelefonTipi={"Hiçbiri","GSM","CDMA","SIP"};
String[] veriAktivitesi={"Yok","Gelen","Giden","Gelen/Giden","Uykuda"};
int[] veriaktiviteiconu={R.drawable.circle_red,R.drawable.circle_green,R.drawable.circle_green,R.drawable.circle_green,R.drawable.circle_red};
int[] veridurumuiconu={R.drawable.circle_red,R.drawable.circle_yellow,R.drawable.circle_green,R.drawable.circle_yellow};
String[] veriDurumu={"Bağlantı kesildi","Bağlanıyor","Bağlandı","","Bağlantı Kesiliyor"};
String[] simDurumu={"Bilinmiyor","Mevcut Değil","Pin Kodu Gerekli","Puk Kodu Gerekli","Ağ Kilitli","Hazır","Hazır Değil","Bloke Edildi","Hatalı","Kısıtlı"};
int[] simdurumuiconu={R.drawable.circle_red,R.drawable.circle_red,R.drawable.circle_red,R.drawable.circle_red,R.drawable.circle_red,R.drawable.circle_green,R.drawable.circle_red,R.drawable.circle_red,R.drawable.circle_red,R.drawable.circle_red};
    String[] standartlar = {"N/A", "802.11a/b/g", "", "", "802.11n", "802.11ac", "802.11ax", "802.11ad", "802.11be"};

    public static Integer getFrequencyFromChannel(int channel) {
        return channelsFrequency.get(channel);
    }

    public static int getChannelFromFrequency(int frequency) {
        return channelsFrequency.indexOf(Integer.valueOf(frequency));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.ayar_menu, menu);


        super.onCreateOptionsMenu(menu, inflater);

    }

    public String mobilBilgi(TelephonyManager telefon) {
        String sonuclar = "";
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        List<CellInfo> cell = telefon.getAllCellInfo();
        for(CellInfo info:cell) {
            CellIdentity cellIdentity= null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                cellIdentity = info.getCellIdentity();

                sonuclar += (String) cellIdentity.getOperatorAlphaLong() + " ";
                sonuclar+=(String)cellIdentity.getOperatorAlphaShort()+" ";

            }
        }
        return sonuclar;
    }
    public String mobilSinyalGucu(TelephonyManager telefon)
    {
        String sonuclar = "";
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        List<CellInfo> cell = telefon.getAllCellInfo();
        for(CellInfo info: cell)
        {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                CellSignalStrength cellSignalStrength=info.getCellSignalStrength();
                sonuclar=cellSignalStrength.getDbm()+" dBm";
            }

        }
        return sonuclar;
    }
    private static String formatIpAddresses(LinkProperties prop) {
        if (prop == null) return null;
        Iterator<LinkAddress> iter = prop.getLinkAddresses().iterator();
        // If there are no entries, return null
        if (!iter.hasNext()) return null;
        // Concatenate all available addresses, comma separated
        String addresses = "";
        while (iter.hasNext()) {
            addresses += iter.next().getAddress();
            if (iter.hasNext()) addresses += ", ";
        }
        return addresses;
    }
    private static String[] formatCihazIpAddresses(LinkProperties prop) {
        if (prop == null) return null;
       // Iterator<LinkAddress> iter = prop.getLinkAddresses().iterator();
        List<LinkAddress> adres=prop.getLinkAddresses();
        List<InetAddress> dnsServer=prop.getDnsServers();
         List<RouteInfo> routeInfos=prop.getRoutes();

        String[] adresler;
        adresler = new String[5];
        for(int i=0;i<adres.size();i++)
        {   if(adres.size()<2)
            adresler[1-i]=adres.get(i).getAddress().toString().replace("/","");
            else
            adresler[i]=adres.get(i).getAddress().toString().replace("/","");
        }
        int i=2;
        adresler[2]="";
        for(InetAddress dns:dnsServer)
        {
            adresler[2]+=dns.getHostAddress()+"\n";

        }
        adresler[3]="";
        adresler[4]="";
      //  adresler[3]=routeInfos.get(3).getGateway().getHostAddress();
     //   adresler[4]=routeInfos.get(2).getDestination().getAddress().toString().replace("/","")+"/"+routeInfos.get(2).getDestination().getPrefixLength();
        for(RouteInfo route:routeInfos)
        {
           // if (route.hasGateway())
            if(route.isDefaultRoute())
                adresler[3]+=route.getGateway().getHostAddress();
                if((!route.isDefaultRoute())&&route.getDestination().getPrefixLength()<=32)
                adresler[4]+=/*route.getDestination().getAddress().getHostName()+route.getDestination().getAddress().toString().replace("/","")+"/"+route.getDestination().getPrefixLength()+*/route.getDestination().toString();
        }


        return adresler;
        /*for(LinkAddress item:adres)
        {
            item.getAddress().toString();
        }*/

    /* for(LinkAddress item:iter)
     {
         item.toString();
     }*/



        // If there are no entries, return null
       /* if (!iter.hasNext()) return null;
        // Concatenate all available addresses, comma separated
        String addresses = "";
        while (iter.hasNext()) {
         addresses+=iter.toString();

            //   addresses += iter.next().getAddress();
           // if (iter.hasNext()) addresses += ", ";
        }
        return addresses;*/
    }
}