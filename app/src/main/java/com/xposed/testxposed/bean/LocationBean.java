package com.xposed.testxposed.bean;

public class LocationBean {

    // 基站类型，1：为空表示只使用gps定位， 2：gms， 3：cdma
    private int baseStationType;
    private double latitude;
    private double longitude;
    private int gsmLac;
    private int gmsCid;
    private int cdmaBaseStationLatitude;
    private int cdmaBaseStationLongitude;
    private int cdmaBaseStationId;
    private int cdmaSystemId;
    private int cdmaNetworkId;

    public int getBaseStationType() {
        return baseStationType;
    }

    public void setBaseStationType(int baseStationType) {
        this.baseStationType = baseStationType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getGsmLac() {
        return gsmLac;
    }

    public void setGsmLac(int gsmLac) {
        this.gsmLac = gsmLac;
    }

    public int getGmsCid() {
        return gmsCid;
    }

    public void setGmsCid(int gmsCid) {
        this.gmsCid = gmsCid;
    }

    public int getCdmaBaseStationLatitude() {
        return cdmaBaseStationLatitude;
    }

    public void setCdmaBaseStationLatitude(int cdmaBaseStationLatitude) {
        this.cdmaBaseStationLatitude = cdmaBaseStationLatitude;
    }

    public int getCdmaBaseStationLongitude() {
        return cdmaBaseStationLongitude;
    }

    public void setCdmaBaseStationLongitude(int cdmaBaseStationLongitude) {
        this.cdmaBaseStationLongitude = cdmaBaseStationLongitude;
    }

    public int getCdmaBaseStationId() {
        return cdmaBaseStationId;
    }

    public void setCdmaBaseStationId(int cdmaBaseStationId) {
        this.cdmaBaseStationId = cdmaBaseStationId;
    }

    public int getCdmaSystemId() {
        return cdmaSystemId;
    }

    public void setCdmaSystemId(int cdmaSystemId) {
        this.cdmaSystemId = cdmaSystemId;
    }

    public int getCdmaNetworkId() {
        return cdmaNetworkId;
    }

    public void setCdmaNetworkId(int cdmaNetworkId) {
        this.cdmaNetworkId = cdmaNetworkId;
    }

    @Override
    public String toString() {
        return "LocationBean{" +
                "baseStationType=" + baseStationType +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", gsmLac=" + gsmLac +
                ", gmsCid=" + gmsCid +
                ", cdmaBaseStationLatitude=" + cdmaBaseStationLatitude +
                ", cdmaBaseStationLongitude=" + cdmaBaseStationLongitude +
                ", cdmaBaseStationId=" + cdmaBaseStationId +
                ", cdmaSystemId=" + cdmaSystemId +
                ", cdmaNetworkId=" + cdmaNetworkId +
                '}';
    }
}
