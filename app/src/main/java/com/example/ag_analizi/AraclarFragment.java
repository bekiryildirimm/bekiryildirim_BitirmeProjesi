package com.example.ag_analizi;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AraclarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AraclarFragment extends Fragment {
EditText sorguIpEdit,baslangicPortEditText,bitisPortEditText;
RadioButton pingRadiobtn,tracerouteRadiobtn,portScanRadiobtn,yayginRadioBtn,hepsiRadioBtn;
ConstraintLayout udpProbeClyt,ipv6TercihClyt,portTaramaHeaderClyt;
LinearLayout portlarLlyt,portlarikinciLlyt;
TextView suAnkiPortTv,kalanPortSayisiTv,portTaramaIlerlemeTv;
ProgressBar portTaramaProgressBar;

RecyclerView recyclerView;
PingAdapter adapter;
List<PingItems> items;
private boolean durBaslabutoncheck=false;
int[] iconlar={R.drawable.circle_grey, R.drawable.circle_green};

    androidx.appcompat.widget.Toolbar toolbar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AraclarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AraclarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AraclarFragment newInstance(String param1, String param2) {
        AraclarFragment fragment = new AraclarFragment();
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
      //  androidx.appcompat.widget.Toolbar toolbar = getView().findViewById(R.id.toolbar);
        BilgiActivity bilgiActivity=new BilgiActivity();
items=new ArrayList<PingItems>();

      //  NavController navController= Navigation.findNavController(bilgiActivity,R.id.navHostFragment);


       // NavigationUI.setupWithNavController(toolbar,navController);

       // TextView textTitle=getView().findViewById(R.id.textTitle);
       /* navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());

            }
        });*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     try {
         for (int i = 0; i < 30; i++)
             items.add(new PingItems(iconlar[1], i, "192.168.1.1", i, "google.com"));
         items.sort(new PingComparator());
     }
     catch (Exception e)
     {
         portTaramaIlerlemeTv.setText(e.getMessage()+" \ne");
     }


        adapter=new PingAdapter(items);
        recyclerView=view.findViewById(R.id.araclarRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
      // View view=inflater.inflate(R.layout.activity_bilgi,container,false);
        //toolbar = view.findViewById(R.id.toolbar);
//return view;
        //return inflater.inflate(R.layout.fragment_araclar, container, false);
        View view=inflater.inflate(R.layout.fragment_araclar, container, false);
        sorguIpEdit=view.findViewById(R.id.sorguipEditText);
                baslangicPortEditText=view.findViewById(R.id.baslangicPortEditText);
        bitisPortEditText=view.findViewById(R.id.bitisPortEditText);
                pingRadiobtn=view.findViewById(R.id.pingRadiobtn);
        tracerouteRadiobtn=view.findViewById(R.id.tracerouteRadiobtn);
                portScanRadiobtn=view.findViewById(R.id.portScanRadiobtn);
        yayginRadioBtn=view.findViewById(R.id.yayginRadioBtn);
                hepsiRadioBtn=view.findViewById(R.id.hepsiRadioBtn);
        udpProbeClyt=view.findViewById(R.id.udpProbeClyt);
                ipv6TercihClyt=view.findViewById(R.id.ipv6TercihClyt);
        portlarLlyt=view.findViewById(R.id.portlarLlyt);
        portlarikinciLlyt=view.findViewById(R.id.portlarikinciLlyt);
        suAnkiPortTv=view.findViewById(R.id.suAnkiPortTv);
                kalanPortSayisiTv=view.findViewById(R.id.kalanPortSayisiTv);
        portTaramaIlerlemeTv=view.findViewById(R.id.portTaramaIlerlemeTv);
                portTaramaHeaderClyt=view.findViewById(R.id.portTaramaHeaderClyt);
        portTaramaProgressBar=view.findViewById(R.id.portTaramaProgressBar);

    //pingRadiobtn.setActivated(true);
pingRadiobtn.setChecked(true);
yayginRadioBtn.setChecked(true);
portlarLlyt.setVisibility(View.GONE);
udpProbeClyt.setVisibility(View.GONE);
pingRadiobtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       ipv6TercihClyt.setVisibility(View.VISIBLE);
        portlarLlyt.setVisibility(View.GONE);
        udpProbeClyt.setVisibility(View.GONE);
    }
});
tracerouteRadiobtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ipv6TercihClyt.setVisibility(View.VISIBLE);
        portlarLlyt.setVisibility(View.GONE);
        udpProbeClyt.setVisibility(View.VISIBLE);
    }
});
portScanRadiobtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ipv6TercihClyt.setVisibility(View.VISIBLE);
        portlarLlyt.setVisibility(View.VISIBLE);
        udpProbeClyt.setVisibility(View.GONE);
        yayginRadioBtn.setChecked(true);
        portlarikinciLlyt.setVisibility(View.GONE);
    }
});

hepsiRadioBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ipv6TercihClyt.setVisibility(View.VISIBLE);
        portlarikinciLlyt.setVisibility(View.VISIBLE);
    }
});
yayginRadioBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ipv6TercihClyt.setVisibility(View.VISIBLE);
        portlarikinciLlyt.setVisibility(View.GONE);
    }
});
        return  view;


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dur_basla_menu,menu);

         MenuItem menuItem=menu.getItem(0);
        SpannableString dur = new SpannableString("Başla");
        dur.setSpan(new ForegroundColorSpan(Color.GREEN), 0, dur.length(), 0);
        menuItem.setTitle(dur);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(!durBaslabutoncheck)
        {
            SpannableString dur = new SpannableString("Dur");
            dur.setSpan(new ForegroundColorSpan(Color.RED), 0, dur.length(), 0);
            item.setTitle(dur);
durBaslabutoncheck=true;
return true;
        }
        else if(durBaslabutoncheck)
        {

            SpannableString dur = new SpannableString("Başla");
            dur.setSpan(new ForegroundColorSpan(Color.GREEN), 0, dur.length(), 0);
            item.setTitle(dur);
durBaslabutoncheck=false;
return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean pingKomutu(String ipadresi){
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process  mIpAddrProcess = runtime.exec("/system/bin/ping -s 128 -c 1 -w 1 -i 0.2 "+ipadresi);
            boolean mExitValue = false;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                mExitValue = mIpAddrProcess.waitFor(200, TimeUnit.MILLISECONDS);
            }
            System.out.println(" mExitValue "+mExitValue);
            //    Process process=runtime.exec("ip neigh show");
            //     process.waitFor();

            return mExitValue;
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
}