package com.appdevgenie.smartpi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.appdevgenie.smartpi.R;
import com.appdevgenie.smartpi.adapters.InfoAdapter;
import com.appdevgenie.smartpi.models.PiStatus;
import com.appdevgenie.smartpi.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String PI_CONTROL = "pi_control";
    public static final String STATUS_CHILD = "status";
    public static final String USERS_CHILD = "users";

    private final String TAG = getClass().getSimpleName();

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TextView tvOnline;
    private TextView tvTemp;
    private ImageView ivTemp;
    private ImageView ivOnline;
    private ImageButton ibInputs, ibOutputs;
    private ImageButton ibSecurity;
    private RecyclerView rvSecurity, rvControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFirebaseAuth();

    }

    private void setupFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){
                    firebaseAuth.signInAnonymously();
                    init();
                }else{
                    init();
                }

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    private void init() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child(PI_CONTROL);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                Log.d(TAG, "new token: " + instanceIdResult.getToken());

                final User user = new User();
                user.setUser_id(firebaseAuth.getCurrentUser().getUid());
                user.setToken(instanceIdResult.getToken());
                //DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child(USERS_CHILD)
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user);
            }
        });

        ConstraintLayout clStatus = findViewById(R.id.include_status_display);
        tvOnline = clStatus.findViewById(R.id.text_online);
        ivOnline = clStatus.findViewById(R.id.image_online);
        tvTemp = clStatus.findViewById(R.id.text_temp);
        ivTemp = clStatus.findViewById(R.id.image_temp);

        ArrayList<String> source = new ArrayList<>();
        source.add("Front door");
        source.add("Back door");
        source.add("Garage");
        source.add("Gate");
        source.add("Lounge");
        source.add("Kitchen");
        source.add("Bathroom");

        ConstraintLayout clSecurity = findViewById(R.id.include_security_info);
        rvSecurity = clSecurity.findViewById(R.id.rvSecurity);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        rvSecurity.setLayoutManager(gridLayoutManager);
        rvSecurity.setHasFixedSize(false);
        InfoAdapter infoAdapter = new InfoAdapter(source);
        rvSecurity.setAdapter(infoAdapter);

        ibSecurity = clSecurity.findViewById(R.id.ibSecurity);
        //ibSecurity.setOnClickListener(this);


        /*ibInputs = findViewById(R.id.ibInputs);
        ibInputs.setOnClickListener(this);
        ibOutputs = findViewById(R.id.ibOutputs);
        ibOutputs.setOnClickListener(this);*/

        setupStatusListener();
    }

    private void setupStatusListener() {

        Query query = databaseReference.child(STATUS_CHILD);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChanged: " + String.valueOf(dataSnapshot.getValue()));


                PiStatus piStatus = dataSnapshot.getValue(PiStatus.class);
                if (piStatus != null) {
                    //tvIP.setText(piStatus.getIp());

                    if (piStatus.isOnline()) {
                        tvOnline.setText("Online");
                        ImageViewCompat.setImageTintList(ivOnline, ColorStateList.valueOf(Color.GREEN));
                        float temperature = piStatus.getTemperature();

                        if(temperature > 1000){
                            tvTemp.setText(TextUtils.concat("- - . - -", " \u00b0C"));
                        }else {
                            tvTemp.setText(TextUtils.concat(
                                    String.format(Locale.getDefault(), "%.2f", temperature), " \u00b0C"));
                            if(temperature < 15){
                                ImageViewCompat.setImageTintList(ivTemp, ColorStateList.valueOf(Color.BLUE));
                            }else if(temperature > 15 && temperature < 22){
                                ImageViewCompat.setImageTintList(ivTemp, ColorStateList.valueOf(Color.GREEN));
                            }else if(temperature > 22 && temperature < 30){
                                ImageViewCompat.setImageTintList(ivTemp, ColorStateList.valueOf(Color.YELLOW));
                            }else{
                                ImageViewCompat.setImageTintList(ivTemp, ColorStateList.valueOf(Color.RED));
                            }
                        }

                    } else {
                        tvOnline.setText("Offline");
                        ImageViewCompat.setImageTintList(ivOnline, ColorStateList.valueOf(Color.RED));
                        tvTemp.setText("- - . - -");

                    }

                    /*if (piStatus.isNotify()) {
                        tvNotify.setText("Notification is ON");
                        tbNotify.setChecked(true);
                    } else {
                        tvNotify.setText("Notification is OFF");
                        tbNotify.setChecked(false);
                    }*/

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    /*@Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.ibSecurity:
                Intent intent = new Intent(MainActivity.this, SecurityItemActivity.class);
                startActivity(intent);
                break;

            *//*case R.id.ibInputs:
                Intent intent = new Intent(MainActivity.this, InputChannelsActivity.class);
                startActivity(intent);
                break;

            case R.id.bOutputs:

                break;*//*
        }
    }*/
}
