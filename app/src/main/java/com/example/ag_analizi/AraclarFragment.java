package com.example.ag_analizi;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AraclarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AraclarFragment extends Fragment {
    EditText sorguIpEdit, baslangicPortEditText, bitisPortEditText;
    RadioButton pingRadiobtn, tracerouteRadiobtn, portScanRadiobtn, yayginRadioBtn, hepsiRadioBtn;
    ConstraintLayout udpProbeClyt, ipv6TercihClyt, portTaramaHeaderClyt;
    LinearLayout portlarLlyt, portlarikinciLlyt;
    TextView suAnkiPortTv, kalanPortSayisiTv, portTaramaIlerlemeTv;
    ProgressBar portTaramaProgressBar;
    SwitchCompat ipv6TercihSwitch, udpProbeSwitch;
    MenuItem menuItem;
    RecyclerView recyclerView;
    PingAdapter adapter;
    List<PingItems> items;
    Thread thread;
    Runnable runnable;
    Handler handler = new Handler();
    private float elapsedTime;
    private boolean durBaslabutoncheck = false;
    int[] iconlar = {R.drawable.circle_grey, R.drawable.circle_green};

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
        BilgiActivity bilgiActivity = new BilgiActivity();
        items = new ArrayList<PingItems>();

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

  /*   try {
         for (int i = 0; i < 30; i++)
             items.add(new PingItems(iconlar[1], i, "192.168.1.1", i, "google.com"));
         items.sort(new PingComparator());
     }
     catch (Exception e)
     {
         portTaramaIlerlemeTv.setText(e.getMessage()+" \ne");
     }*/


        adapter = new PingAdapter(items);
        recyclerView = view.findViewById(R.id.araclarRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //  recyclerView.setAdapter(adapter);


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
        View view = inflater.inflate(R.layout.fragment_araclar, container, false);
        sorguIpEdit = view.findViewById(R.id.sorguipEditText);
        baslangicPortEditText = view.findViewById(R.id.baslangicPortEditText);
        bitisPortEditText = view.findViewById(R.id.bitisPortEditText);
        pingRadiobtn = view.findViewById(R.id.pingRadiobtn);
        tracerouteRadiobtn = view.findViewById(R.id.tracerouteRadiobtn);
        portScanRadiobtn = view.findViewById(R.id.portScanRadiobtn);
        yayginRadioBtn = view.findViewById(R.id.yayginRadioBtn);
        hepsiRadioBtn = view.findViewById(R.id.hepsiRadioBtn);
        udpProbeClyt = view.findViewById(R.id.udpProbeClyt);
        ipv6TercihClyt = view.findViewById(R.id.ipv6TercihClyt);
        portlarLlyt = view.findViewById(R.id.portlarLlyt);
        portlarikinciLlyt = view.findViewById(R.id.portlarikinciLlyt);
        suAnkiPortTv = view.findViewById(R.id.suAnkiPortTv);
        kalanPortSayisiTv = view.findViewById(R.id.kalanPortSayisiTv);
        portTaramaIlerlemeTv = view.findViewById(R.id.portTaramaIlerlemeTv);
        portTaramaHeaderClyt = view.findViewById(R.id.portTaramaHeaderClyt);
        portTaramaProgressBar = view.findViewById(R.id.portTaramaProgressBar);
        ipv6TercihSwitch = view.findViewById(R.id.ipv6TercihSwitch);
        udpProbeSwitch = view.findViewById(R.id.udpProbeSwitch);
        // Editable editable =sorguIpEdit.getText();
        //   portTaramaIlerlemeTv.setText(sorguIpEdit.getText().length()+" e");

        //pingRadiobtn.setActivated(true);
        sorguIpEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!durBaslabutoncheck && sorguIpEdit.getText().length() != 0) {
                    SpannableString dur = new SpannableString("Başla");
                    dur.setSpan(new ForegroundColorSpan(Color.GREEN), 0, dur.length(), 0);
                    menuItem.setTitle(dur);

                } else if (!durBaslabutoncheck && sorguIpEdit.getText().length() == 0) {
                    SpannableString dur = new SpannableString("Başla");
                    dur.setSpan(new ForegroundColorSpan(Color.GRAY), 0, dur.length(), 0);
                    menuItem.setTitle(dur);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
        return view;


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dur_basla_menu, menu);

        menuItem = menu.getItem(0);
        SpannableString dur = new SpannableString("Başla");
        dur.setSpan(new ForegroundColorSpan(Color.GRAY), 0, dur.length(), 0);
        menuItem.setTitle(dur);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (!durBaslabutoncheck && sorguIpEdit.getText().length() != 0) {
            SpannableString dur = new SpannableString("Dur");
            dur.setSpan(new ForegroundColorSpan(Color.RED), 0, dur.length(), 0);
            item.setTitle(dur);
            durBaslabutoncheck = true;
            if (pingRadiobtn.isChecked()) {

                PingGonder(sorguIpEdit.getText().toString(), true);


                //portTaramaIlerlemeTv.setText("ping"+ipv6TercihSwitch.isChecked());
            } else if (tracerouteRadiobtn.isChecked()) {
                PingGonder(sorguIpEdit.getText().toString(), false);

                // portTaramaIlerlemeTv.setText("traceroute"+ipv6TercihSwitch.isChecked()+udpProbeSwitch.isChecked());
            }

/*else if (pingRadiobtn.isChecked()&&!ipv6TercihSwitch.isChecked()) {

}
else if(tracerouteRadiobtn.isChecked()&&ipv6TercihSwitch.isChecked()&&udpProbeSwitch.isChecked())
{
    portTaramaIlerlemeTv.setText("traceroute"+ipv6TercihSwitch.isChecked());
}
else if(tracerouteRadiobtn.isChecked()&&ipv6TercihSwitch.isChecked()&&!udpProbeSwitch.isChecked())
{

}*/


            return true;
        } else if (durBaslabutoncheck && sorguIpEdit.getText().length() != 0) {

            SpannableString dur = new SpannableString("Başla");
            dur.setSpan(new ForegroundColorSpan(Color.GREEN), 0, dur.length(), 0);
            item.setTitle(dur);
            durBaslabutoncheck = false;
            kalanPortSayisiTv.setText(thread.isAlive() + " " + thread.isInterrupted());
            thread.interrupt();
            portTaramaIlerlemeTv.setText(thread.isAlive() + " " + thread.isInterrupted());

            return true;
        } else if (durBaslabutoncheck && sorguIpEdit.getText().length() == 0) {
            SpannableString dur = new SpannableString("Başla");
            dur.setSpan(new ForegroundColorSpan(Color.GRAY), 0, dur.length(), 0);
            item.setTitle(dur);
            durBaslabutoncheck = false;
            //kalanPortSayisiTv.setText(thread.isAlive() + " " + thread.isInterrupted());
           // thread.interrupt();
            portTaramaIlerlemeTv.setText(thread.isAlive() + " " + thread.isInterrupted());
            return true;
        } else if (!durBaslabutoncheck && sorguIpEdit.getText().length() == 0) {
            SpannableString dur = new SpannableString("Başla");
            dur.setSpan(new ForegroundColorSpan(Color.GRAY), 0, dur.length(), 0);
            item.setTitle(dur);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private boolean pingKomutu(String ipadresi) {
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("/system/bin/ping -s 128 -c 1 -w 1 -i 0.2 " + ipadresi);
            boolean mExitValue = false;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                mExitValue = process.waitFor(200, TimeUnit.MILLISECONDS);
            }
            System.out.println(" mExitValue " + mExitValue);
            //    Process process=runtime.exec("ip neigh show");
            //     process.waitFor();

            return mExitValue;
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
            System.out.println(" Exception:" + ignore);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" Exception:" + e);
        }
        return false;
    }

    private String parseTimeFromPing(String ping) {
        String time = "";
        if (ping.contains("time=")) {
            int index = ping.indexOf("time=");

            time = ping.substring(index + 5);
            index = time.indexOf(" ");
            time = time.substring(0, index);
        }

        return time;
    }

    private String parseIpToPingFromPing(String ping) {
        String ip = "";
        if (ping.contains("PING")) {
            // Get ip when ping succeeded
            int indexOpen = ping.indexOf("(");
            int indexClose = ping.indexOf(")");

            ip = ping.substring(indexOpen + 1, indexClose);
        }

        return ip;
    }

    private String parseIpFromPing(String ping) {
        String ip = "";
        if (ping.contains(FROM_PING)) {
            // Get ip when ttl exceeded
            int index = ping.indexOf(FROM_PING);

            ip = ping.substring(index + 5);
            if (ip.contains(PARENTHESE_OPEN_PING)) {
                // Get ip when in parenthese
                int indexOpen = ip.indexOf(PARENTHESE_OPEN_PING);
                int indexClose = ip.indexOf(PARENTHESE_CLOSE_PING);

                ip = ip.substring(indexOpen + 1, indexClose);
            } else {
                // Get ip when after from
                ip = ip.substring(0, ip.indexOf("\n"));
                if (ip.contains(":")) {
                    index = ip.indexOf(":");
                } else {
                    index = ip.indexOf(" ");
                }

                ip = ip.substring(0, index);
            }
        } else {
            // Get ip when ping succeeded
            int indexOpen = ping.indexOf(PARENTHESE_OPEN_PING);
            int indexClose = ping.indexOf(PARENTHESE_CLOSE_PING);

            ip = ping.substring(indexOpen + 1, indexClose);
        }

        return ip;
    }

    private static final String PING = "PING";
    private static final String FROM_PING = "From";
    private static final String SMALL_FROM_PING = "from";
    private static final String PARENTHESE_OPEN_PING = "(";
    private static final String PARENTHESE_CLOSE_PING = ")";
    private static final String TIME_PING = "time=";
    private static final String EXCEED_PING = "exceed";
    private static final String UNREACHABLE_PING = "100%";

    private String tracerouteKomutu(String ipadresi, int ttl) {
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        String s;
        String res = "";
        long startTime; // = System.nanoTime();
        elapsedTime = 0;
        try {

            Process process = runtime.exec("/system/bin/ping -s 128 -c 1 -t " + ttl + " " + ipadresi);
            startTime = System.nanoTime();
            process.waitFor();
            elapsedTime = (System.nanoTime() - startTime) / 1000000.0f;
            String mExitValue = "";
            // if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //  mExitValue = process.waitFor(200, TimeUnit.MILLISECONDS);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            //     BufferedReader bufferedReader1=new BufferedReader(new InputStreamReader(process.getErrorStream()));
            res += "zaman:\n" + elapsedTime + "  --->\n";
            while ((s = bufferedReader.readLine()) != null) {
                res += s + "\n";
            }
            res += "\n---->" + parseTimeFromPing(res)+"   " +parseIpFromPing(res)+"   "+parseIpToPingFromPing(res)+ "<---";


            //  }
            System.out.println(" mExitValue " + mExitValue);
            //    Process process=runtime.exec("ip neigh show");
            //     process.waitFor();

            return res;
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
            System.out.println(" Exception:" + ignore);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" Exception:" + e);
        }
        return "error";
    }

public String PingHostnameStr(String str)
{
    int index1=-1,index2=-1;
    if(str.contains("from"))
    {   index1=str.indexOf("from");
        index2=str.indexOf("(", str.indexOf("from"));
        if(index1==-1||index2==-1)
        {
            //index2=str.indexOf(": icmp", str.indexOf("from"));
return "";
        }
    }
    else if(str.contains("From"))
    {
        index1=str.indexOf("From");
        index2=str.indexOf("(", str.indexOf("From"));

        if(index1==-1||index2==-1)
        {
          //  index2=str.indexOf(": icmp", str.indexOf("From"));
return "";
        }
    }
    else
    {    return "";}

    return str.substring(index1+4,index2);
    //else if(str.contains("From"))
}
    public String pingIpAdresiStr(String str)
    {
        int index1=-1,index2=-1;
        if(str.contains("from"))
        {   //index1=str.indexOf("from");
            index1=str.indexOf("(", str.indexOf("from"));
            index2=str.indexOf(")",index1);
            if(index1==-1||index2==-1)
            {
               // index2=str.indexOf(": icmp", str.indexOf("from"));
                index1=str.indexOf("from");
                index2=str.indexOf(": icmp", str.indexOf("from"));


                index1+=4;
            }
            else {
                index1+=1;
            }
        }
       else if(str.contains("From"))
        {   //index1=str.indexOf("from");
            index1=str.indexOf("(", str.indexOf("From"));
            index2=str.indexOf(")",index1);
            if(index1==-1||index2==-1)
            {
                // index2=str.indexOf(": icmp", str.indexOf("from"));
                index1=str.indexOf("From");
                index2=str.indexOf(": icmp", str.indexOf("From"));
index1+=4;
            }
            else {
                index1+=1;
            }
        }
       else{return "";}

       if(index1==-1||index2==-1)
           return "hata";
       else
        return str.substring(index1,index2);

     /*   else if(str.contains("From"))
        {
            index1=str.indexOf("From");
            index2=str.indexOf("(", str.indexOf("From"));

            if(index2==-1)
            {
                index2=str.indexOf(": icmp", str.indexOf("From"));

            }
        }*/
    }
    private boolean PingProcess(String ipadresi,int sirano,String ttl)
    {
        Runtime runtime=Runtime.getRuntime();
        String s;
        String res = "";
        int iconIndex;
        boolean cikisbool=false;
        try {
            Process process=runtime.exec("/system/bin/ping  -s 128 -c 1 -w 1 -W 1 "+ttl +" "+ ipadresi);
          int cikisDegeri= process.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((s = bufferedReader.readLine()) != null) {
                res += s + "\n";
            }


            if(cikisDegeri==0) {
                cikisbool = true;
            iconIndex=1;

            }
           else {
                cikisbool = false;
           iconIndex=0;

           }

           //items.add(new PingItems(iconlar[iconIndex],sirano,ipadresi,cikisDegeri+" ",denemepingstr(res)));
            //  items.add(new PingItems(iconlar[iconIndex],sirano,ipadresi,cikisDegeri+" ",res));
          //  items.add(new PingItems(iconlar[iconIndex],sirano,pingIpAdresiStr(res),cikisDegeri+" ",PingHostnameStr(res)));
            items.add(new PingItems(iconlar[iconIndex],sirano,pingIpAdresiStr(res),cikisDegeri+" ",res));
         return cikisbool;
        }
        catch (InterruptedException intr)
        {

        }
        catch (IOException ioe)
        {

        }
        catch (Exception e)
        {
portTaramaIlerlemeTv.setText(e.getMessage()+" eror");
            items.add(new PingItems(R.drawable.circle_red,sirano,pingIpAdresiStr(res),"",res));
        }
return false;

    }




    private String pingKomutu2(String ipadresi) {

        Runtime runtime = Runtime.getRuntime();
        String s;
        String res = "";
        long startTime; // = System.nanoTime();
        elapsedTime = 0;
        try {

            Process process = runtime.exec("/system/bin/ping -s 128 -c 1 " + ipadresi);
            startTime = System.nanoTime();
            process.waitFor();
            elapsedTime = (System.nanoTime() - startTime) / 1000000.0f;
            String mExitValue = "";
            // if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //  mExitValue = process.waitFor(200, TimeUnit.MILLISECONDS);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            //     BufferedReader bufferedReader1=new BufferedReader(new InputStreamReader(process.getErrorStream()));
            res += "zaman:\n" + elapsedTime + "  --->\n";
            while ((s = bufferedReader.readLine()) != null) {
                res += s + "\n";
            }
          //  res += "\n---->" + parseTimeFromPing(res) + "<---";
            res += "\n---->" + parseTimeFromPing(res)+"   " +parseIpFromPing(res)+"   "+parseIpToPingFromPing(res)+ "<---";

            //  }
            System.out.println(" mExitValue " + mExitValue);
            //    Process process=runtime.exec("ip neigh show");
            //     process.waitFor();

            return res;
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
            System.out.println(" Exception:" + ignore);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" Exception:" + e);
        }
        return "error";
    }
//boolean pingcheck=true;
    public void PingGonder(String ipAdresi, boolean hangisi) {

        items.clear();
        runnable = new Runnable() {
            @Override
            public void run() {
                final boolean[] pingcheck = {true};
                for (int i = 1; durBaslabutoncheck&& pingcheck[0]; i++) {
                    int finalI = i;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (this) {
                                if (hangisi) {
                                    // items.add(new PingItems(iconlar[1], finalI, ipAdresi, finalI + " MSsd", pingKomutu2(ipAdresi)));
                                PingProcess(ipAdresi,finalI,"");
                                    items.sort(new PingComparator());
                                }

                                else if (!hangisi) {
                                    // items.add(new PingItems(iconlar[1], finalI, ipAdresi, finalI + " MSsd", tracerouteKomutu(ipAdresi, finalI)));

                                  /* durBaslabutoncheck=*/

                                    boolean traceCheck=PingProcess(ipAdresi,finalI,"-t "+finalI);

                                    if(traceCheck){

                                        pingcheck[0] =false;
                                        kalanPortSayisiTv.setText("pingcheck=false");
                                        SpannableString dur = new SpannableString("Başla");
                                        dur.setSpan(new ForegroundColorSpan(Color.GREEN), 0, dur.length(), 0);
                                        menuItem.setTitle(dur);
                                    }

                                        items.sort(new PingComparator());


                                }


                                recyclerView.setAdapter(adapter);
                            }
                        }
                    });
                    try {


                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                }
            }
        };
        thread = new Thread(runnable);
thread.start();

    }
}