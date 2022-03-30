package com.example.exc_11;

public class Manufacturer {
    private String name;
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
