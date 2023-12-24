package com.example.ag_analizi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AraclarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AraclarFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
       View view=inflater.inflate(R.layout.activity_bilgi,container,false);
        toolbar = view.findViewById(R.id.toolbar);
//return view;
        return inflater.inflate(R.layout.fragment_araclar, container, false);
    }

   /* @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.ayar_menu,menu);


        super.onCreateOptionsMenu(menu, inflater);

    }*/
}