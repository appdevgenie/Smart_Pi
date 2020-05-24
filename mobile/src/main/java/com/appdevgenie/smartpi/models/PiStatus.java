package com.appdevgenie.smartpi.models;

public class PiStatus {

    private String ip;
    private float temperature;
    private float pressure;
    private long lastOnline;
    private boolean online;
    private boolean notify;

    public PiStatus() {
    }

    public PiStatus(String ip, float temperature, float pressure, long lastOnline, boolean online, boolean notify) {
        this.ip = ip;
        this.temperature = temperature;
        this.pressure = pressure;
        this.lastOnline = lastOnline;
        this.online = online;
        this.notify = notify;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public long getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(long lastOnline) {
        this.lastOnline = lastOnline;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }
}
