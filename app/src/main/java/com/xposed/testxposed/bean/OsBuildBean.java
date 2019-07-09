package com.xposed.testxposed.bean;

public class OsBuildBean {

    private String model;
    private String manufacturer;
    private String brand;
    private String hardware;
    private String board;
    private String serial;
    private String device;
    private String id;
    private String product;
    private String display;
    private String fingerprint;
    private String cpu1;
    private String versionRelease;
    private String radioVersion;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getCpu1() {
        return cpu1;
    }

    public void setCpu1(String cpu1) {
        this.cpu1 = cpu1;
    }

    public String getVersionRelease() {
        return versionRelease;
    }

    public void setVersionRelease(String versionRelease) {
        this.versionRelease = versionRelease;
    }

    public String getRadioVersion() {
        return radioVersion;
    }

    public void setRadioVersion(String radioVersion) {
        this.radioVersion = radioVersion;
    }

    @Override
    public String toString() {
        return "OsBuildBean{" +
                "model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", brand='" + brand + '\'' +
                ", hardware='" + hardware + '\'' +
                ", board='" + board + '\'' +
                ", serial='" + serial + '\'' +
                ", device='" + device + '\'' +
                ", id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", display='" + display + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", cpu1='" + cpu1 + '\'' +
                ", versionRelease='" + versionRelease + '\'' +
                ", radioVersion='" + radioVersion + '\'' +
                '}';
    }
}
