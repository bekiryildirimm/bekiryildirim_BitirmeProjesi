package com.example.ag_analizi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.os.Handler;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LanTaramaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanTaramaFragment extends Fragment {
TextView suankiIp, kalanSayisi, agAdi;
ProgressBar progressBar;
Handler handler=new Handler(); /*{
    @Override
    public void handleMessage(@NonNull Message msg/*,int finalI,String[] ip,String subnetIp) {
        super.handleMessage(msg);
        progressBar.setProgress(finalI * 100 / 254);
        kalanSayisi.setText((255 - finalI) + " kaldı");
        ip[0] = subnetIp + finalI;
        suankiIp.setText(ip[0]);
        //items.add(new LanTaramaItems("", "", "192.168.1." + finalI, ""));

        //  recyclerView.setAdapter(adapter);

        if (pingKomutu(ip[0]) && finalI != 0 && finalI != 255) {
            items.add(new LanTaramaItems("", "", "192.168.1." + finalI, ""));

            recyclerView.setAdapter(adapter);
        }
    }
};*/
    Runnable runnable;
    Thread thread;

    List<LanTaramaItems> items;
RecyclerView recyclerView;
LanTaramaAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LanTaramaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LanTaramaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LanTaramaFragment newInstance(String param1, String param2) {
        LanTaramaFragment fragment = new LanTaramaFragment();
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
        items=new ArrayList<LanTaramaItems>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_lan_tarama, container, false);

        View view=inflater.inflate(R.layout.fragment_lan_tarama, container, false);
        suankiIp=view.findViewById(R.id.suAnkiIpTv);
                kalanSayisi=view.findViewById(R.id.kalanSayisiTv);
        agAdi=view.findViewById(R.id.lanTaramaAgAdiTv);
        progressBar=view.findViewById(R.id.lanTaramaProgressBar);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  for(int i=0;i<30;i++)
      //  items.add(new LanTaramaItems("1","2","3","4"));

        adapter=new LanTaramaAdapter(items);
        recyclerView=view.findViewById(R.id.lanTaramaRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    //    recyclerView.setAdapter(adapter);
        /*try{
        pingGonder();}
        catch (Exception e)
        {
            suankiIp.setText(e.getMessage());
        }*/

        suankiIp.setText(pingKomutu("192.168.1."+0)+" ");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
      inflater.inflate(R.menu.dur_basla_menu,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


               suankiIp.setVisibility(View.VISIBLE);
               kalanSayisi.setVisibility(View.VISIBLE);
               progressBar.setVisibility(View.VISIBLE);
               pingGonder();
        if(!thread.isAlive())
        {
            suankiIp.setVisibility(View.GONE);
            kalanSayisi.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }



        return super.onOptionsItemSelected(item);
    }
    //String subnetIp="192.168.1.";
   // String ip;
  //  int i=0;

    int i,finalI;
    String[] ip;
    String subnetIp;
public  void pingGonder()
{
    agAdi.setText(AgBilgisiFragment.AgSsid);
    //items.clear();
    //adapter=new VeriKullanimiAdapter(items);
    //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
/*runnable=new Runnable() {
    @Override
    public void run() {
        String subnetIp="192.168.1.";
        final String[] ip = new String[1];
        for(int i=0;i<=255;i++) {

            int finalI = i;


            synchronized (this) {
                try {
                    {
                        wait(100);
                    }
                }
                catch (Exception e)
                {

                }
                progressBar.setProgress(finalI * 100 / 254);
                kalanSayisi.setText((255 - finalI) + " kaldı");
                ip[0] = subnetIp + finalI;
                suankiIp.setText(ip[0]);
                //items.add(new LanTaramaItems("", "", "192.168.1." + finalI, ""));

                //  recyclerView.setAdapter(adapter);

                if (pingKomutu(ip[0]) && finalI != 0 && finalI != 255) {
                    items.add(new LanTaramaItems("", "", "192.168.1." + finalI, ""));

                    recyclerView.setAdapter(adapter);
                }
            }
            handler.sendEmptyMessage(0);
/*            try {


                Thread.sleep(200);}
            catch (Exception e)
            {

            }
        }
    }
};*/

    runnable=new Runnable() {
        @Override
        public void run() {
            String subnetIp="192.168.1.";
            final String[] ip = new String[1];
            for(int i=0;i<=255;i++) {

                int finalI = i;
                //int finalI1 = i;
                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        synchronized (this) {
                            progressBar.setProgress(finalI * 100 / 254);
                            kalanSayisi.setText((255 - finalI) + " kaldı");
                            ip[0] = subnetIp + finalI;
                            suankiIp.setText(ip[0]);
                            //items.add(new LanTaramaItems("", "", "192.168.1." + finalI, ""));

                            //  recyclerView.setAdapter(adapter);

                            if (pingKomutu(ip[0])&&finalI!=0&&finalI!=255) {
                                items.add(new LanTaramaItems("", "", "192.168.1." + finalI, ""));

                                recyclerView.setAdapter(adapter);
                            }
                            if(finalI==255)
                            {
                                suankiIp.setVisibility(View.GONE);
                                kalanSayisi.setVisibility(View.GONE);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                });
                try {


                    Thread.sleep(200);}
                catch (Exception e)
                {

                }
            }
        }
    };

thread=new Thread(runnable);
thread.start();



/*new Thread(new Runnable() {
    @Override
    public void run() {
        String subnetIp="192.168.1.";
        final String[] ip = new String[1];
        for(int i=0;i<=255;i++) {

            int finalI = i;
            //int finalI1 = i;
            handler.post(new Runnable() {
                @Override
                public void run() {


                    synchronized (this) {
                        progressBar.setProgress(finalI * 100 / 254);
                        kalanSayisi.setText((255 - finalI) + " kaldı");
                        ip[0] = subnetIp + finalI;
                        suankiIp.setText(ip[0]);
                        //items.add(new LanTaramaItems("", "", "192.168.1." + finalI, ""));

                      //  recyclerView.setAdapter(adapter);

                        if (pingKomutu(ip[0])&&finalI!=0&&finalI!=255) {
                            items.add(new LanTaramaItems("", "", "192.168.1." + finalI, ""));

                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            });
            try {


            Thread.sleep(200);}
            catch (Exception e)
            {

            }
        }
    }
}).start();*/

     // pingKomutu(ip);
       /* if(pingKomutu(ip))
        {
            items.add(new LanTaramaItems("","","192.168.1."+i,""));
            handler.post(new Runnable() {
                @Override
                public void run() {

                    recyclerView.setAdapter(adapter);
                }
            });


        }*/


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
