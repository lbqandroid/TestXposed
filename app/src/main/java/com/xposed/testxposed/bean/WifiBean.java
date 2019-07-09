package com.xposed.testxposed.bean;

public class WifiBean {

    private String macAddress;
    private String bssid;
    private String ssid;
    private int ipAddress;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(int ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "WifiBean{" +
                "macAddress='" + macAddress + '\'' +
                ", bssid='" + bssid + '\'' +
                ", ssid='" + ssid + '\'' +
                ", ipAddress=" + ipAddress +
                '}';
    }
}
