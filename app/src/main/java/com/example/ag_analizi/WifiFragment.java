package com.example.ag_analizi;

import static android.os.Build.VERSION_CODES.TIRAMISU;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.MloLink;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WifiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WifiFragment extends Fragment {
String[] channelwid={"20MHz","40MHz","80MHz","160MHz","80+80MHz","320MHz"};
WifiManager wifiManager;
    RecyclerView recyclerView;
    List<ScanResult> scanResults;
    List<wifiSinyalitems> items;
    WifiTaramaAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WifiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WifiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WifiFragment newInstance(String param1, String param2) {
        WifiFragment fragment = new WifiFragment();
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
   //     adapter=new WifiTaramaAdapter()
       items=new ArrayList<wifiSinyalitems>();
        //items.add(new wifiSinyalitems("1","2","3","4","5","6"));
   wifiManager=(WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);

  //wifiManager.startScan();
        if (wifiManager.startScan()) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            scanResults = wifiManager.getScanResults();

            for (ScanResult sr : scanResults) {
              /*  List<MloLink> mloLinks= null;
                if (Build.VERSION.SDK_INT >= TIRAMISU) {
                    mloLinks = sr.getAffiliatedMloLinks();
                }
                int chan=1;

      for(MloLink ml:mloLinks)
      {
          if (Build.VERSION.SDK_INT >= TIRAMISU) {
              chan=ml.getChannel();
          }
      }*/

                items.add(new wifiSinyalitems(channelwid[sr.channelWidth], sr.SSID,/*Integer.toString(sr.level)*/sr.level+" dBm",sr.BSSID,sr.capabilities,sr.frequency+""));
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     /*   View view=inflater.inflate(R.layout.fragment_wifi, container, false);
        recyclerView=view.findViewById(R.id.wifiTaramaRecyclerView);
     //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new WifiTaramaAdapter(getActivity().getApplicationContext(), items));
        return view;*/
        return inflater.inflate(R.layout.fragment_wifi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //for(int i=0;i<30;i++)
       // items.add(new wifiSinyalitems("15","25","3","4","5","6"));

        adapter=new WifiTaramaAdapter(items);
        recyclerView=view.findViewById(R.id.wifiTaramaRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}