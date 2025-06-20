package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.model.Vehicle;

import java.util.List;

public interface VehicleDao {
    public List<Vehicle> findByPriceRange();
    public List<Vehicle> findByMakeAndModel();
    public List<Vehicle> findByYearRange();
    public List<Vehicle> findByColor();
    public List<Vehicle> findByMileageRange();
    public List<Vehicle> findByType();
    public List<Vehicle> getAllVehicles();
    public List<Vehicle> addVehicle();
    public List<Vehicle>  removeVehicle();
    public List<Vehicle>  extractVehicles();
}
