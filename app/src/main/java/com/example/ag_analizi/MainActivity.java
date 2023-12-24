package com.example.ag_analizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.ag_analizi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Handler handler=new Handler();
    // Used to load the 'ag_analizi' library on application startup.
    /*static {
        System.loadLibrary("ag_analizi");
    }*/

 //  private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //  binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
    setContentView(R.layout.activity_main);
      //  getActionBar().hide();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent=new Intent(MainActivity.this,BilgiActivity.class);
          startActivity(intent);
          finish();
        }
    },3000);



        // Example of a call to a native method
       // TextView tv = binding.sampleText;
        //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'ag_analizi' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}