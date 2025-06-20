package com.pluralsight.CarDealershipAPI.model;

public class Vehicle {
    private int vin;
    private String make;
    private String model;
    private String color;
    private int year;
    private String vehicleType;
    private int mileage;
    private double price;
    private boolean sold;

    public Vehicle() {
    }

    public Vehicle(int vin, String make, String model, String color, int year, String vehicleType, int mileage, double price, boolean sold) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.vehicleType = vehicleType;
        this.mileage = mileage;
        this.price = price;
        this.sold = sold;
    }

    public int getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getMileage() {
        return mileage;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
