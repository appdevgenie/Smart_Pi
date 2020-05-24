package com.appdevgenie.smartpi.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.appdevgenie.smartpi.R;
import com.appdevgenie.smartpi.adapters.SecuritySpinnerAdapter;

public class SecurityItemActivity extends AppCompatActivity {

    private Context context;
    private Spinner spinner;

    private int[] icons;
    private String[] names;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_item);

        init();
    }

    private void init() {

        context = getApplicationContext();

        //icons = getResources().getIntArray(R.array.icons);
        icons = new int[]{
                R.drawable.ic_garage,
                R.drawable.ic_bathroom,
                R.drawable.ic_door,
                R.drawable.ic_gate,
                R.drawable.ic_kitchen,
                R.drawable.ic_lounge
        };
        names = getResources().getStringArray(R.array.icon_names);

        spinner = findViewById(R.id.spSecurityDescription);
        SecuritySpinnerAdapter securitySpinnerAdapter = new SecuritySpinnerAdapter(context, icons, names);
        spinner.setAdapter(securitySpinnerAdapter);

    }


}
