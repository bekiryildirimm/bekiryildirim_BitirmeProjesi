package com.example.ag_analizi;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.Manifest;
import android.app.AppOpsManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class BilgiActivity extends AppCompatActivity {
  // DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi);
       /* if(ContextCompat.checkSelfPermission(BilgiActivity.this,Manifest.permission.ACCESS_FINE_LOCATION))
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }*/
        AppOpsManager appop=(AppOpsManager) getSystemService(APP_OPS_SERVICE);
        int mode=appop.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,android.os.Process.myUid(),getPackageName());
        if(mode!=AppOpsManager.MODE_ALLOWED)
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));


        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                // No location access granted.
                            }
                        }
                );

// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });





      //  showPopup(new View());
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBar actionBar = getSupportActionBar();

       // getActionBar().hide();
        final DrawerLayout drawerLayout=findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView=findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController= Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,navController);

        TextView textTitle=findViewById(R.id.textTitle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());

            }
        });
    }
/*public void showPopup(View v)
{
    PopupMenu popup=new PopupMenu(this,v);
    MenuInflater inflater=popup.getMenuInflater();
    inflater.inflate(R.menu.ayar_menu,popup.getMenu());
    popup.show();
}*/
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // MenuInflater inflater=getMenuInflater();
         //       inflater.inflate(R.menu.ayar_menu,menu);
        getMenuInflater().inflate(R.menu.ayar_menu,menu);
        return true;
    }*/
}