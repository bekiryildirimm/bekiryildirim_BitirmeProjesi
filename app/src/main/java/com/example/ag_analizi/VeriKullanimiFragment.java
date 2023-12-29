package com.example.ag_analizi;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.RemoteException;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VeriKullanimiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VeriKullanimiFragment extends Fragment {

    Runnable runnable;
    Thread thread;
    Handler handler=new Handler();

    TextView ToolbarTv,VeriTv;
ImageView ToolbarOkbtn,ToolbarTakvimbtn;
int gun=-1;
int agTuru=ConnectivityManager.TYPE_WIFI;
long butun;
NetworkStatsManager stats;
NetworkStats netstat;
NetworkStats.Bucket bucket;
MenuItem usageitem;
    RecyclerView recyclerView;
    List<VeriKullanimiItems> items;
    VeriKullanimiAdapter adapter;
    PackageManager pm;
    List<ApplicationInfo> info;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VeriKullanimiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VeriKullanimiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VeriKullanimiFragment newInstance(String param1, String param2) {
        VeriKullanimiFragment fragment = new VeriKullanimiFragment();
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
        items=new ArrayList<VeriKullanimiItems>();
      pm= getActivity().getPackageManager();
      info=pm.getInstalledApplications(PackageManager.GET_META_DATA);
  stats=(NetworkStatsManager) getActivity().getApplicationContext().getSystemService(Context.NETWORK_STATS_SERVICE);
       // VeriTv.setText(Long.toString((ButunCihaz(stats, ConnectivityManager.TYPE_WIFI,Milisaniye(gun)/(1024*1024))))); //Long.toString((ButunCihaz(stats, ConnectivityManager.TYPE_WIFI,Milisaniye(gun)/(1024*1024))));
      // String str=Long.toString((ButunCihaz(stats, ConnectivityManager.TYPE_WIFI,Milisaniye(gun)/(1024*1024))))+"MB";
     //  VeriTv.setText("veli");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_veri_kullanimi, container, false);
        ToolbarOkbtn=view.findViewById(R.id.veriToolbarOkbtn);
        ToolbarTv=view.findViewById(R.id.veritoolbarTv);
        ToolbarTakvimbtn=view.findViewById(R.id.veriToolbarTakvimbtn);
        VeriTv=view.findViewById(R.id.veriTv);
        ToolbarOkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickers(v);
            }

        });
        ToolbarTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickers(v);
            }

        });
        ToolbarTakvimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickers(v);
            }
        });
       // VeriTv.setText(Long.toString((ButunCihaz(stats, ConnectivityManager.TYPE_WIFI,Milisaniye(gun))/(1024*1024))));
        return view;
        //return inflater.inflate(R.layout.fragment_veri_kullanimi, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
      // butun= ButunCihaz(stats, ConnectivityManager.TYPE_WIFI,Milisaniye(gun))/(1024*1024);
       // VeriTv.setText(Long.toString(butun)+"Mb");

    }

    public long Milisaniye(int gun)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,gun);
        return calendar.getTimeInMillis();
    }
    public void onClickers(View v) {
        PopupMenu popupMenu=new PopupMenu(getActivity().getApplicationContext(), ToolbarOkbtn);
        popupMenu.getMenuInflater().inflate(R.menu.veri_kullanimi_toolbar, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ToolbarTv.setText(item.getTitle());
                /*items.clear();
                adapter=new VeriKullanimiAdapter(items);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);*/
              //  if(item.getItemId()==)

                if(item.getItemId()==R.id.menu_app_time_today)
                {
                    secenekbtn(agTuru,-1);
                }
                else if(item.getItemId()==R.id.menu_app_time_last_7)
                {
                    secenekbtn(agTuru,-7);
                }
                else if(item.getItemId()==R.id.menu_app_time_last_30)
                {
                    secenekbtn(agTuru,-30);
                }



                return true;
            }
        });

        popupMenu.show();

        //  popupMenu.setOnMenuItemClickListener();
        // ToolbarTv.setText("evet");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // for (int i=0;i<30;i++)
         //   items.add(new VeriKullanimiItems(null,(i*3),"1","2"));
       /* for(ApplicationInfo ai:info)
        {
            try {
                //Drawable drawable=ai.i
              //  (String)pm.getApplicationLabel(ai);
                items.add(new VeriKullanimiItems(ai.loadIcon(pm), 50, (String)pm.getApplicationLabel(ai) , "5"));
            }
            catch (Exception e)
            {

            }

            }*/
        UygulamalaricinSorgu();
        adapter=new VeriKullanimiAdapter(items);
        recyclerView=view.findViewById(R.id.veriKullanimRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      //  recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
       /* if(menu instanceof MenuBuilder)
            ((MenuBuilder)menu).setOptionalIconsVisible(true);*/
        inflater.inflate(R.menu.usage_menu,menu);


        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


       if(item.getItemId()==R.id.usagemenubtns)
       {
           usageitem=item;
           return true;
       }


       else if(item.getItemId()==R.id.wifiMenubtn)
       {
           SpannableString s = new SpannableString("Wifi");
           s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
           //item.setTitle(s);
           usageitem.setTitle(s);
           secenekbtn(ConnectivityManager.TYPE_WIFI,gun);
           return true;
       }
       else if(item.getItemId()==R.id.mobilMenubtn)
       {
           usageitem.setTitle("Mobil");
           secenekbtn(ConnectivityManager.TYPE_MOBILE,gun);
           return true;
       }
        else
            item.setTitle("s2");
            return super.onOptionsItemSelected(item);
    }

    public long ButunCihaz(NetworkStatsManager stats,int agTuru,long milisaniye)
    {

        long rx,tx;
        try {


            bucket = stats.querySummaryForDevice(agTuru, null, milisaniye, System.currentTimeMillis());
        }
        catch (RemoteException e)
        {

        }
        return bucket.getTxBytes()+ bucket.getRxBytes();


    }
    public void secenekbtn(int agTuru,int gun)
    {
        this.agTuru=agTuru;
        this.gun=gun;
        items.clear();



        UygulamalaricinSorgu();



       // adapter=new VeriKullanimiAdapter(items);
       // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        //ButunCihaz(stats,agTuru,Milisaniye(gun));
     //   VeriTv.setText(Long.toString((ButunCihaz(stats,agTuru,Milisaniye(gun))/(1024*1024)))+"MB");

    }
    public void UygulamalaricinSorgu() {

        butun = ButunCihaz(stats, agTuru, Milisaniye(gun)) / (1024);

String birim="";
        float butunf=(float)butun;
        butunf/=1024.0f;
       if(butun/(1024*1024)==0)
       {
           birim=" MB";
       }
       else if(butun/(1024*1024)!=0)
       {
           butunf/=1024.0f;
           birim=" GB";
       }





        VeriTv.setText(Float.toString(butunf).substring(0,Float.toString(butunf).indexOf(".")+2) + birim);
        runnable = new Runnable() {
            @Override
            public void run() {
                final long[] rx = new long[1];
                final long[] tx = new long[1];
                final boolean[] check = new boolean[1];
                int progc;
                for (ApplicationInfo ai : info) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {


                                netstat = stats.queryDetailsForUid(agTuru, null, Milisaniye(gun), System.currentTimeMillis(), ai.uid);


                                rx[0] = 0;
                                tx[0] = 0;
                                check[0] = netstat.hasNextBucket();
                                while (netstat.hasNextBucket()) {
                                    if (bucket.getUid() == ai.uid) {
                                        rx[0] += (bucket.getRxBytes() + bucket.getTxBytes());
                                        //tx+=bucket.getTxBytes();
                                    }
                                    netstat.getNextBucket(bucket);
                                }
                                netstat.close();
                                long rx2=rx[0];
                                rx[0] /= (1024);
                            String birim2="";
                            float rxf=(float)rx[0];
                            rxf/=1024.0f;
                           if(rx[0]==0)
                           {
                               check[0]=false;
                           }
                           else if(rx[0]/1024==0)
                            {

                               rxf=rx2/1024.0f;
                               birim2=" KB";
                            }
                          else if(rx[0]/(1024*1024)==0)
                            {
                                birim2=" MB";
                            }
                            else if(rx[0]/(1024*1024)!=0)
                            {
                                rxf/=1024.0f;
                                birim2=" GB";
                            }
                                if (check[0]) {
                                    items.add(new VeriKullanimiItems(ai.loadIcon(pm), (((int) rx[0] * 100) / (int) butun), (String) pm.getApplicationLabel(ai), Float.toString(rxf).substring(0,Float.toString(rxf).indexOf(".")+2) + birim2, (int) rx[0]));

                                    items.sort(new veriComparator());
                                    recyclerView.setAdapter(adapter);
                                }

                        }
                    });
                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {

                    }
                }

            }
        };
        thread = new Thread(runnable);
        thread.start();
      /*  for(ApplicationInfo ai:info)
        {
            netstat=stats.queryDetailsForUid(agTuru,null,Milisaniye(gun),System.currentTimeMillis(),ai.uid);


            rx=0;
            tx=0;
            check=netstat.hasNextBucket();
            while(netstat.hasNextBucket())
            {
                if(bucket.getUid()==ai.uid)
                {
                    rx+=(bucket.getRxBytes()+bucket.getTxBytes());
                    //tx+=bucket.getTxBytes();
                }
                netstat.getNextBucket(bucket);
            } netstat.close();
            rx/=(1024*1024);

            if(check) {
                items.add(new VeriKullanimiItems(ai.loadIcon(pm), (((int) rx*100) / (int) butun), (String) pm.getApplicationLabel(ai), rx + "MB"/*+(((int) rx*100) / ((int) butun)),(int)rx));
            }

            // items.add(new VeriKullanimiItems(ai.loadIcon(pm), 50, (String)pm.getApplicationLabel(ai) , "5"));

        }
        items.sort(new veriComparator());
        // items.sort(Comparator<VeriKullanimiItems>);
    }*/
    }
    public void UygulamalaricinSorgu1()
    {
        long rx,tx;
        boolean check;
        int progc;
      butun=ButunCihaz(stats,agTuru,Milisaniye(gun))/(1024*1024);
      VeriTv.setText(butun+"MB");
       for(ApplicationInfo ai:info)
       {
        netstat=stats.queryDetailsForUid(agTuru,null,Milisaniye(gun),System.currentTimeMillis(),ai.uid);


   rx=0;
   tx=0;
   check=netstat.hasNextBucket();
    while(netstat.hasNextBucket())
    {
     if(bucket.getUid()==ai.uid)
     {
     rx+=(bucket.getRxBytes()+bucket.getTxBytes());
     //tx+=bucket.getTxBytes();
     }
     netstat.getNextBucket(bucket);
    } netstat.close();
   rx/=(1024*1024);

    if(check) {
        items.add(new VeriKullanimiItems(ai.loadIcon(pm), (((int) rx*100) / (int) butun), (String) pm.getApplicationLabel(ai), rx + "MB",(int)rx));
    }

    // items.add(new VeriKullanimiItems(ai.loadIcon(pm), 50, (String)pm.getApplicationLabel(ai) , "5"));

       }
       items.sort(new veriComparator());
      // items.sort(Comparator<VeriKullanimiItems>);
    }

}