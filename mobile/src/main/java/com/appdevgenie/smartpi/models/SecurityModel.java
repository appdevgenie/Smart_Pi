package com.appdevgenie.smartpi.models;

import android.widget.ImageView;

import com.appdevgenie.smartpi.R;

import java.util.ArrayList;

public class SecurityModel {

    private String name;
    private boolean status;
    private int icon;

    public SecurityModel() {
    }

    public SecurityModel(String name, boolean status, int icon) {
        this.name = name;
        this.status = status;
        this.icon = icon;
    }

    public static ArrayList<SecurityModel> loadSecurityModels(){
        ArrayList<SecurityModel> arrayList = new ArrayList<>();

        arrayList.add(new SecurityModel("Front door", false, R.drawable.ic_door));
        arrayList.add(new SecurityModel("Motion front", true, R.drawable.ic_motion_sensor));
        arrayList.add(new SecurityModel("Back door", true, R.drawable.ic_door));
        arrayList.add(new SecurityModel("Front gate", true, R.drawable.ic_gate));
        arrayList.add(new SecurityModel("Lounge", false, R.drawable.ic_lounge));
        arrayList.add(new SecurityModel("Bathroom", true, R.drawable.ic_bathroom));
        arrayList.add(new SecurityModel("Kitchen", false, R.drawable.ic_kitchen));
        arrayList.add(new SecurityModel("Left garage", true, R.drawable.ic_garage));
        arrayList.add(new SecurityModel("Right garage", true, R.drawable.ic_garage));

        return arrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
