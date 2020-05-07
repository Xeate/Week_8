package com.test.android.w8;

public class Bottle {

    private String product;

    private String manufacturer;

    private double total_energy;

    private double size;

    private double price;


    public Bottle(String name, String manuf, double totE, double s, double p){
        product = name;
        manufacturer = manuf;
        total_energy = totE;
        size = s;
        price = p;
    }

    public String getName() {
        return product;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getEnergy() {
        return total_energy;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}