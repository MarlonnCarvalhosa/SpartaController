package com.marlonn.spartacontroller.spartacontroller.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.marlonn.spartacontroller.spartacontroller.R;
import com.marlonn.spartacontroller.spartacontroller.fragments.Dia15Fragment;
import com.marlonn.spartacontroller.spartacontroller.fragments.Dia25Fragment;
import com.marlonn.spartacontroller.spartacontroller.fragments.Dia5Fragment;
import com.marlonn.spartacontroller.spartacontroller.utils.FragmentoUtils;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dia5:
                    FragmentoUtils.replace(MainActivity.this, new Dia5Fragment());
                    return true;
                case R.id.navigation_dia15:
                    FragmentoUtils.replace(MainActivity.this, new Dia15Fragment());
                    return true;
                case R.id.navigation_dia25:
                    FragmentoUtils.replace(MainActivity.this, new Dia25Fragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentoUtils.replace(MainActivity.this, new Dia5Fragment());
        
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
