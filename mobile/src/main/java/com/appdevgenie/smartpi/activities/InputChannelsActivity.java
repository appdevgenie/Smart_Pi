package com.appdevgenie.smartpi.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appdevgenie.smartpi.R;
import com.appdevgenie.smartpi.adapters.InputChannelAdapter;
import com.appdevgenie.smartpi.models.Input;

import java.util.ArrayList;

public class InputChannelsActivity extends AppCompatActivity {

    private Context context;
    private ArrayList<Input> inputArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private InputChannelAdapter inputChannelAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyclerview);

        init();
    }

    private void init() {

        context = getApplicationContext();
        recyclerView = findViewById(R.id.recyclerView);
        inputArrayList = Input.loadInputSensors();
        inputChannelAdapter = new InputChannelAdapter(context, inputArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(inputChannelAdapter);
    }
}
