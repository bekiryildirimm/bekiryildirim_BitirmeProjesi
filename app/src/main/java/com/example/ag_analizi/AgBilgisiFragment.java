package com.example.ag_analizi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.RouteInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.CellIdentity;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;*/

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;
import java.net.URLConnection;

import kotlinx.coroutines.android.HandlerDispatcher;
/*import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;*/

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgBilgisiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgBilgisiFragment extends Fragment {
   public static String AgSsid;
   public static String httresponse;
public static int subnetUzunlugu;
public static byte[] subnetRaw;
public  static String subnetAdresi;
    WifiManager wifiManager;
    NetworkRequest networkRequest;
    Stream<String> arpre;
    WifiInfo wifiInfo;
    TelephonyManager telephonyManager;
    ConnectivityManager connectivityManager,connectivityManager2;
    NetworkCapabilities networkCapabilities;
    ConnectivityManager.NetworkCallback networkCallback;
    LinkProperties linkProperties;
    LinkProperties lp;
    List<RouteInfo> routes;
    Network[] networks;
    TextView wifiBaglantiTitletv,baglantiTuruTv, hariciIpTv, hariciIpv6, ipadresTv, subnetMaskTv, gatewayIpTv, dnsServerIpTv, ipv6AdresiTv, gatewayIpv6Tv, dnsServerIpv6Tv, etkinTv, baglantiDurumuTv, dhcpKiraTv, ssidTv, bssidTv, saticiTv, kanalTv, standartTv, hizTv, maxHizTv, wifiSignalTv, veriDurumTv, veriAktiviteTv, veriDolasimTv, simDurumTv, simOperatorTv, simMccTv, agTipiTv, telefonTipiTv, mobilSinyalTv;
    ImageView etkinIcon, baglantiTuruIcon, veriDurumIcon, veriAktiviteIcon, veriDolasimIcon, simDurumIcon,baglantiDurumuIcon;

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


       /* try {
            networkRequest = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .build();

            networkCallback = new ConnectivityManager.NetworkCallback(ConnectivityManager.NetworkCallback.FLAG_INCLUDE_LOCATION_INFO) {
                @Override
                public void onAvailable(@NonNull Network network) {
                    super.onAvailable(network);
                }

                @Override
                public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
                    super.onLinkPropertiesChanged(network, linkProperties);
                    etkinTv.setText("link properties changed");
                }

                @Override
                public void onLost(@NonNull Network network) {
                    super.onLost(network);
                    etkinTv.setText("onlost");
                }
            };
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
        }
        catch (Exception e)
        {
            etkinTv.setText(e.getMessage()+"  hatanetwork");
        }*/

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        connectivityManager2.unregisterNetworkCallback(networkCallback);
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
       // hariciIpv6 = view.findViewById(R.id.HariciIPv6Tv);
        ipadresTv = view.findViewById(R.id.ipAdresTv);
        subnetMaskTv = view.findViewById(R.id.subnetMaskTv);
        gatewayIpTv = view.findViewById(R.id.gatewayIpTv);
        dnsServerIpTv = view.findViewById(R.id.dnsServerIpTv);
        ipv6AdresiTv = view.findViewById(R.id.ipv6AdresiTv);
        //gatewayIpv6Tv = view.findViewById(R.id.gatewayIpv6Tv);
        //dnsServerIpv6Tv = view.findViewById(R.id.dnsServerIpv6Tv);
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

        wifiBaglantiTitletv=view.findViewById(R.id.wifiBaglantiTitletv);
        baglantiDurumuIcon=view.findViewById(R.id.baglantiDurumuIcon);
        BaglantiKontrol();




        try {
            connectivityManager2=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            networkRequest = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .build();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                networkCallback = new ConnectivityManager.NetworkCallback(ConnectivityManager.NetworkCallback.FLAG_INCLUDE_LOCATION_INFO);

            }
            else
            {
                networkCallback=new ConnectivityManager.NetworkCallback(){
                    @Override
                    public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
                        super.onLinkPropertiesChanged(network, linkProperties);
                 //       etkinTv.setText("onlink");
                        try{
                            BaglantiKontrol();}
                        catch (Exception e)
                        {
                            etkinTv.setText("1"+e.getMessage()+" gata");
                        }

                    }

                    @Override
                    public void onAvailable(@NonNull Network network) {
                        super.onAvailable(network);
                       // etkinTv.setText("onavailable");
                        try{
                            BaglantiKontrol();}
                        catch (Exception e)
                        {
                            etkinTv.setText("2"+e.getMessage()+" gata");
                        }
                    }

                    @Override
                    public void onLost(@NonNull Network network) {
                        super.onLost(network);
                     //   etkinTv.setText("onlost");
                        try{
                        BaglantiKontrol();}
                        catch (Exception e)
                        {
                            etkinTv.setText("3"+e.getMessage()+" gata");
                        }
                    }

                    @Override
                    public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                        super.onCapabilitiesChanged(network, networkCapabilities);
                        try{
                            BaglantiKontrol();}
                        catch (Exception e)
                        {
                            etkinTv.setText("4"+e.getMessage()+" gata");
                        }
                     //   etkinTv.setText("capabilities");
                    }
                };

            }
           // connectivityManager2.registerNetworkCallback(networkRequest, networkCallback);
            connectivityManager2.registerDefaultNetworkCallback(networkCallback);
        }

        catch (Exception e)
        {
           etkinTv.setText("5"+e.getMessage()+"  hatanetwork");
        }




        return view;
    }

    public String httpdenem(String url,boolean hangisi)
    {

        String[] mesaj = new String[1];
        String mesaj2;
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
Response.Listener<String> responsee=new Response.Listener<String>() {
    public String mesaj;
    @Override
    public void onResponse(String response) {
        mesaj=response;

        if(hangisi)
            hariciIpTv.setText(response);
        else
            saticiTv.setText(response);
    }
};

class resp implements Response.Listener<String>
{
String mesaj;

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    @Override
    public void onResponse(String response) {
        setMesaj(response);
         AgBilgisiFragment.httresponse=response;
        if(hangisi)
            hariciIpTv.setText(response);
        else
            saticiTv.setText(response);
    }
}
resp respo=new resp();
        Request<String> request=new StringRequest(Request.Method.GET,url,respo/*(Response.Listener<String>) response-> {
String mesaj1;
mesaj1=response;
mesaj[0]=mesaj1;
reference.set(response);
            try {
            this.httresponse=response;

                if(hangisi)
                  hariciIpTv.setText(mesaj[0]);
                else
                    saticiTv.setText(response);


            }
            catch (Exception e)
            {
         hariciIpTv.setText(e.getMessage()+" null");
            }
        }*/,              (Response.ErrorListener) error -> {
            // make a Toast telling the user
            // that something went wrong
          //  Toast.makeText(getActivity().getApplicationContext(), "Some error occurred! Cannot fetch dog image", Toast.LENGTH_LONG).show();
            // log the error message in the error stream
            Log.e("BilgiActivity", "loadDogImage error: ${error.localizedMessage}"+error.getLocalizedMessage());
        });
        requestQueue.add(request);
    return "";
        //    return requestQueue.getResponseDelivery().toString();
        //return httresponse;
    }
    private final static AtomicReference<String> reference=new AtomicReference<>();
    private final String[] rValue=new String[1];
    private static String rtv="";
    public static String retValue(String value)
    {
        rtv=value;
        return value;
    }
    public String httpdenemee(String url,boolean hangisi)
    {
        final String[] mesaj = new String[1];
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());

        Request<String> request=new StringRequest(Request.Method.GET,url,(Response.Listener<String>) response-> {
            String mesaj1;
          reference.set(response);
          rValue[0]+=response;

   String birs=retValue(response);
   etkinTv.setText(birs+rtv);
        },              (Response.ErrorListener) error -> {

        });
        requestQueue.add(request);

        return rValue[0];
    }

    public void BaglantiKontrol()
    {

        try {


            wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
            wifiInfo = wifiManager.getConnectionInfo();
            connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            linkProperties = connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork());
            telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);

            networks = connectivityManager.getAllNetworks();
        }
        catch (Exception e)
        {
            etkinTv.setText("6"+e.getMessage()+" ne oldu bakalım");

        }
try {

    if (linkProperties.getInterfaceName().contains("ccmni")) {
        baglantiTuruTv.setText("Mobil");
        baglantiTuruIcon.setImageResource(R.drawable.circle_green);
        wifiBaglantiTitletv.setText("MOBİL BAĞLANTI");
      /*  etkinIcon.setImageResource(R.drawable.circle_red);
        etkinTv.setText("Değil");*/
    } else if (linkProperties.getInterfaceName().contains("wlan")) {
        baglantiTuruTv.setText("Wi-Fi");
        baglantiTuruIcon.setImageResource(R.drawable.circle_green);
        wifiBaglantiTitletv.setText("WİFİ BAĞLANTISI");
       /* etkinIcon.setImageResource(R.drawable.circle_green);
        etkinTv.setText("Evet");*/
    }
    else
    {
        etkinIcon.setImageResource(R.drawable.circle_red);
        etkinTv.setText("Değil");
    }
    if(wifiManager.isWifiEnabled())
    {
        etkinIcon.setImageResource(R.drawable.circle_green);
        etkinTv.setText("Evet");
        ssidTv.setText(wifiInfo.getSSID());
        try {
            dhcpKiraTv.setText(wifiManager.getDhcpInfo().leaseDuration + "saniye");
        } catch (Exception a) {

        }
        bssidTv.setText(wifiInfo.getBSSID());
        kanalTv.setText(getChannelFromFrequency(wifiInfo.getFrequency()) + " (" + wifiInfo.getFrequency() + "MHz)");

        hizTv.setText(wifiInfo.getLinkSpeed() + " Mbps");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            maxHizTv.setText(wifiInfo.getMaxSupportedRxLinkSpeedMbps() + " / " + wifiInfo.getMaxSupportedTxLinkSpeedMbps() + " Mbps");
        }
        wifiSignalTv.setText(wifiInfo.getRssi() + " dBm");
    }
    else if(!wifiManager.isWifiEnabled())
    {
        dhcpKiraTv.setText("N/A");
        ssidTv.setText("N/A");
        bssidTv.setText("N/A");
        saticiTv.setText("N/A");
        kanalTv.setText("N/A");
        hizTv.setText("N/A");
        maxHizTv.setText("N/A");
        wifiSignalTv.setText("N/A");
        etkinIcon.setImageResource(R.drawable.circle_red);
        etkinTv.setText("Değil");
    }
}
catch (Exception e)
{
    baglantiTuruTv.setText("Hiçbiri");
    baglantiTuruIcon.setImageResource(R.drawable.circle_red);
}
        try {
            String[] ipadresleri = formatCihazIpAddresses(linkProperties);
            ipadresTv.setText(ipadresleri[1]);
            ipv6AdresiTv.setText(ipadresleri[0]);
            dnsServerIpTv.setText(ipadresleri[2]);
            gatewayIpTv.setText(ipadresleri[3]);
            subnetMaskTv.setText(ipadresleri[4]);
        } catch (Exception e) {
            ipadresTv.setText("N/A");
            ipv6AdresiTv.setText("N/A");
            dnsServerIpTv.setText("N/A");
            gatewayIpTv.setText("N/A");
            subnetMaskTv.setText("N/A");
        }

        String mesaj= httpdenem("https://ipecho.net/plain",true);

        AgSsid=wifiInfo.getSSID();
//        bssidTv.setText(wifiInfo.getBSSID());
        httpdenem("https://api.macvendors.com/"+wifiInfo.getBSSID(),false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            standartTv.setText(standartlar[wifiInfo.getWifiStandard()]);
        } else
            standartTv.setText("N/A");

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
        baglantiDurumuTv.setText(wifiDurumu[wifiManager.getWifiState()]);
        baglantiDurumuIcon.setImageResource(wifiIconu[wifiManager.getWifiState()]);

    }

     String[] wifiDurumu={"Bağlantı Kesiliyor","Bağlantı Kesildi","Bağlanıyor","Bağlandı","Bilinmiyor"};
   int[] wifiIconu={R.drawable.circle_red,R.drawable.circle_red,R.drawable.circle_green,R.drawable.circle_green,R.drawable.circle_red};







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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Intent intent=new Intent(getActivity().getApplicationContext(),);
        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        return super.onOptionsItemSelected(item);
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
       adresler[2]=adresler[2].substring(0,adresler[2].lastIndexOf("\n"));
        adresler[3]="";
        adresler[4]="";
      //  adresler[3]=routeInfos.get(3).getGateway().getHostAddress();
     //   adresler[4]=routeInfos.get(2).getDestination().getAddress().toString().replace("/","")+"/"+routeInfos.get(2).getDestination().getPrefixLength();
        for(RouteInfo route:routeInfos)
        {
           // if (route.hasGateway())
            if(route.isDefaultRoute())
                adresler[3]+=route.getGateway().getHostAddress();
            
                if((!route.isDefaultRoute())&&route.getDestination().getPrefixLength()<=32) {
                    adresler[4] +=/*route.getDestination().getAddress().getHostName()+route.getDestination().getAddress().toString().replace("/","")+"/"+route.getDestination().getPrefixLength()+*/route.getDestination().toString();
  subnetUzunlugu=route.getDestination().getPrefixLength();
  subnetAdresi=route.getDestination().getAddress().toString().replace("/","");
  subnetRaw=route.getDestination().getAddress().getAddress();
                }
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
   /* public String httpdene()
    {
   HttpClient httpClient= new DefaultHttpClient();
        try {
            HttpResponse httpResponse=httpClient.execute(new HttpGet(new URI("https://ipecho.net/plain")));
            StatusLine statusLine=httpResponse.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(out);
                String responseString = out.toString();
                out.close();
                return  responseString;
                //..more logic
            }
            else{
                //Closes the connection.
                httpResponse.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void HttpDeneme()
    {
        class RequestTask extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... uri) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response;
                String responseString = null;
                try {
                    response = httpclient.execute(new HttpGet(uri[0]));
                    StatusLine statusLine = response.getStatusLine();
                    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        response.getEntity().writeTo(out);
                        responseString = out.toString();
                        out.close();
                    } else{
                        //Closes the connection.
                        response.getEntity().getContent().close();
                        throw new IOException(statusLine.getReasonPhrase());
                    }
                } catch (ClientProtocolException e) {
                    //TODO Handle problems..
                } catch (IOException e) {
                    //TODO Handle problems..
                }
                return responseString;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                //Do anything with response..
            }
        }
        new RequestTask().execute("https://ipecho.net/plain");
    }*/
}