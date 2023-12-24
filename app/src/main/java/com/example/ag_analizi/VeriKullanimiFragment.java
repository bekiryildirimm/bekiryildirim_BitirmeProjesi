package com.example.ag_analizi;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VeriKullanimiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VeriKullanimiFragment extends Fragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_veri_kullanimi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* for (int i=0;i<30;i++)
            items.add(new VeriKullanimiItems(null,(i*3),"1","2"));*/
        for(ApplicationInfo ai:info)
        {
            try {
                //Drawable drawable=ai.i
              //  (String)pm.getApplicationLabel(ai);
                items.add(new VeriKullanimiItems(ai.loadIcon(pm), 50, (String)pm.getApplicationLabel(ai) , "5"));
            }
            catch (Exception e)
            {

            }

            }

        adapter=new VeriKullanimiAdapter(items);
        recyclerView=view.findViewById(R.id.veriKullanimRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
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

        else if(item.getItemId()==R.id.hepsiMenubtn)
       {
   usageitem.setTitle("Hepsi");
   return true;
       }
       else if(item.getItemId()==R.id.wifiMenubtn)
       {
           SpannableString s = new SpannableString("Wifi");
           s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
           //item.setTitle(s);
           usageitem.setTitle(s);
           return true;
       }
       else if(item.getItemId()==R.id.mobilMenubtn)
       {
           usageitem.setTitle("Mobil");
           return true;
       }
        else
            item.setTitle("s2");
            return super.onOptionsItemSelected(item);
    }
}