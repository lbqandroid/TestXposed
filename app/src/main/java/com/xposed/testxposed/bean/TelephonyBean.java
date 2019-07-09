package com.xposed.testxposed.bean;

public class TelephonyBean {

    private String imei;
    private String imsi;
    private String line1Number;
    private String simSerialNumber;
    private String simCountryIso;
    private String simOperator;
    private String simOperatorName;
    private String networkCountryIso;
    private String networkOperator;
    private String networkOperatorName;
    private String deviceSoftwareVersion;
    private int phoneCount;
    private int phoneType;
    private int networkType;
    private int simState;
    private boolean hasIccCard;
    private String androidId;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getLine1Number() {
        return line1Number;
    }

    public void setLine1Number(String line1Number) {
        this.line1Number = line1Number;
    }

    public String getSimSerialNumber() {
        return simSerialNumber;
    }

    public void setSimSerialNumber(String simSerialNumber) {
        this.simSerialNumber = simSerialNumber;
    }

    public String getSimCountryIso() {
        return simCountryIso;
    }

    public void setSimCountryIso(String simCountryIso) {
        this.simCountryIso = simCountryIso;
    }

    public String getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(String simOperator) {
        this.simOperator = simOperator;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getNetworkCountryIso() {
        return networkCountryIso;
    }

    public void setNetworkCountryIso(String networkCountryIso) {
        this.networkCountryIso = networkCountryIso;
    }

    public String getNetworkOperator() {
        return networkOperator;
    }

    public void setNetworkOperator(String networkOperator) {
        this.networkOperator = networkOperator;
    }

    public String getNetworkOperatorName() {
        return networkOperatorName;
    }

    public void setNetworkOperatorName(String networkOperatorName) {
        this.networkOperatorName = networkOperatorName;
    }

    public String getDeviceSoftwareVersion() {
        return deviceSoftwareVersion;
    }

    public void setDeviceSoftwareVersion(String deviceSoftwareVersion) {
        this.deviceSoftwareVersion = deviceSoftwareVersion;
    }

    public int getPhoneCount() {
        return phoneCount;
    }

    public void setPhoneCount(int phoneCount) {
        this.phoneCount = phoneCount;
    }

    public int getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }

    public int getNetworkType() {
        return networkType;
    }

    public void setNetworkType(int networkType) {
        this.networkType = networkType;
    }

    public int getSimState() {
        return simState;
    }

    public void setSimState(int simState) {
        this.simState = simState;
    }

    public boolean isHasIccCard() {
        return hasIccCard;
    }

    public void setHasIccCard(boolean hasIccCard) {
        this.hasIccCard = hasIccCard;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    @Override
    public String toString() {
        return "TelephonyBean{" +
                "imei='" + imei + '\'' +
                ", imsi='" + imsi + '\'' +
                ", line1Number='" + line1Number + '\'' +
                ", simSerialNumber='" + simSerialNumber + '\'' +
                ", simCountryIso='" + simCountryIso + '\'' +
                ", simOperator='" + simOperator + '\'' +
                ", simOperatorName='" + simOperatorName + '\'' +
                ", networkCountryIso='" + networkCountryIso + '\'' +
                ", networkOperator='" + networkOperator + '\'' +
                ", networkOperatorName='" + networkOperatorName + '\'' +
                ", deviceSoftwareVersion='" + deviceSoftwareVersion + '\'' +
                ", phoneCount=" + phoneCount +
                ", phoneType=" + phoneType +
                ", networkType=" + networkType +
                ", simState=" + simState +
                ", hasIccCard=" + hasIccCard +
                ", androidId='" + androidId + '\'' +
                '}';
    }
}
