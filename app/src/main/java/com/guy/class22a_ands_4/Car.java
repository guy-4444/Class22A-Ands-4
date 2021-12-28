package com.guy.class22a_ands_4;

public class Car {

    private String model = "";
    private String license = "";

    public Car() { }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public Car setLicense(String license) {
        this.license = license;
        return this;
    }
}
