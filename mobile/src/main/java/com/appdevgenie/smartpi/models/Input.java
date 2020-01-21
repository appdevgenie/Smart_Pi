package com.appdevgenie.smartpi.models;

import java.util.ArrayList;

public class Input {

    private boolean enabled;
    private String channel;
    private String description;
    private int inputType;
    private boolean status;
    private long timestampOpened;
    private long timestampClosed;

    public Input() {
    }

    public Input(boolean enabled, String channel, String description, int inputType, boolean status, long timestampOpened, long timestampClosed) {
        this.enabled = enabled;
        this.channel = channel;
        this.description = description;
        this.inputType = inputType;
        this.status = status;
        this.timestampOpened = timestampOpened;
        this.timestampClosed = timestampClosed;
    }

    public static ArrayList<Input> loadInputSensors(){

        ArrayList<Input> inputArrayList = new ArrayList<>();
        inputArrayList.add(new Input(false, "1", "Description", 0, false, 0, 0));
        inputArrayList.add(new Input(false, "2", "Description", 0, false, 0, 0));
        inputArrayList.add(new Input(false, "3", "Description", 0, false, 0, 0));
        inputArrayList.add(new Input(false, "4", "Description", 0, false, 0, 0));
        inputArrayList.add(new Input(false, "5", "Description", 0, false, 0, 0));
        inputArrayList.add(new Input(false, "6", "Description", 0, false, 0, 0));
        inputArrayList.add(new Input(false, "7", "Description", 0, false, 0, 0));
        inputArrayList.add(new Input(false, "8", "Description", 0, false, 0, 0));

        return inputArrayList;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getTimestampOpened() {
        return timestampOpened;
    }

    public void setTimestampOpened(long timestampOpened) {
        this.timestampOpened = timestampOpened;
    }

    public long getTimestampClosed() {
        return timestampClosed;
    }

    public void setTimestampClosed(long timestampClosed) {
        this.timestampClosed = timestampClosed;
    }
}
