package com.pluralsight.CarDealershipAPI.controller;

import com.pluralsight.CarDealershipAPI.dao.VehicleDao;
import com.pluralsight.CarDealershipAPI.dao.impl.VehicleDAOJDBCImpl;
import com.pluralsight.CarDealershipAPI.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleDao vehicleDao;

    // GET /vehicles?minPrice=10000&maxPrice=30000
    @GetMapping
    public List<Vehicle> searchVehicles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMiles,
            @RequestParam(required = false) Integer maxMiles,
            @RequestParam(required = false) String type
    ) {
        if (minPrice != null && maxPrice != null) {
            return vehicleDao.findByPriceRange(minPrice, maxPrice);
        } else if (make != null && model != null) {
            return vehicleDao.findByMakeAndModel(make, model);
        } else if (minYear != null && maxYear != null) {
            return vehicleDao.findByYearRange(minYear, maxYear);
        } else if (color != null) {
            return vehicleDao.findByColor(color);
        } else if (minMiles != null && maxMiles != null) {
            return vehicleDao.findByMileageRange(minMiles, maxMiles);
        } else if (type != null) {
            return vehicleDao.findByType(type);
        }

        return vehicleDao.getAllVehicles(); // fallback
    }

    // POST /vehicles
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
    }

    //PUT /vehicles/vin
    @PutMapping("/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateVehicle(@PathVariable int vin, @RequestBody Vehicle vehicle) {
        vehicle.setVin(vin);
        return vehicleDao.updateVehicle(vehicle);
    }

    // DELETE /vehicles/vin
    @DeleteMapping("/{vin}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable int vin) {
        boolean removed = vehicleDao.removeVehicle(vin);
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }
    }
}
