package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface VehicleDao {
    public List<Vehicle> findByPriceRange(double minPrice, double maxPrice);
    public List<Vehicle> findByMakeAndModel(String make, String model);
    public List<Vehicle> findByYearRange(int minYear, int maxYear);
    public List<Vehicle> findByColor(String color);
    public List<Vehicle> findByMileageRange(int minMileage, int maxMileage);
    public List<Vehicle> findByType(String type);
    public List<Vehicle> getAllVehicles();
    boolean updateVehicle(Vehicle vehicle);
    public void addVehicle(Vehicle v);
    public boolean removeVehicle(int vin);
    public List<Vehicle>  extractVehicles(ResultSet rs) throws SQLException;
}
