package com.pluralsight.CarDealershipAPI.dao.impl;

import com.pluralsight.CarDealershipAPI.dao.VehicleDao;
import com.pluralsight.CarDealershipAPI.model.Vehicle;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleDAOJDBCImpl implements VehicleDao {
    private DataSource dataSource;

    public VehicleDAOJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> findByPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT * FROM vehicles WHERE Price BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);
            return extractVehicles(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> findByMakeAndModel(String make, String model) {
        String sql = "SELECT * FROM vehicles WHERE VehicleMake LIKE ? OR VehicleModel LIKE ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + (make  != null ? make  : "") + "%");
            ps.setString(2, "%" + (model != null ? model : "") + "%");
            return extractVehicles(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> findByYearRange(int minYear, int maxYear) {
        String sql = "SELECT * FROM vehicles WHERE VehicleYear BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, minYear);
            ps.setInt(2, maxYear);
            return extractVehicles(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> findByColor(String color) {
        String sql = "SELECT * FROM vehicles WHERE Color = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, color);
            return extractVehicles(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> findByMileageRange(int minMileage, int maxMileage) {
        String sql = "SELECT * FROM vehicles WHERE Mileage BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, minMileage);
            ps.setInt(2, maxMileage);
            return extractVehicles(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> findByType(String type) {
        String sql = "SELECT * FROM vehicles WHERE VehicleType = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            return extractVehicles(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement()) {
            return extractVehicles(st.executeQuery("SELECT * FROM vehicles"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateVehicle(Vehicle v) {
        String sql = "UPDATE vehicles SET VehicleMake = ?, VehicleModel = ?, Color = ?, VehicleYear = ?, VehicleType = ?, Mileage = ?, Price = ?, is_sold = ? WHERE Vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getMake());
            ps.setString(2, v.getModel());
            ps.setString(3, v.getColor());
            ps.setInt(4, v.getYear());
            ps.setString(5, v.getVehicleType());
            ps.setInt(6, v.getMileage());
            ps.setDouble(7, v.getPrice());
            ps.setBoolean(8, v.isSold());
            ps.setInt(9, v.getVin()); // WHERE Vin = ?
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addVehicle(Vehicle v) {
        String sql = "INSERT INTO vehicles (Vin, VehicleMake, VehicleModel, Color, VehicleYear, VehicleType, Mileage, Price, is_sold) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, v.getVin());
            ps.setString(2, v.getMake());
            ps.setString(3, v.getModel());
            ps.setString(4, v.getColor());
            ps.setInt(5, v.getYear());
            ps.setString(6, v.getVehicleType());
            ps.setInt(7, v.getMileage());
            ps.setDouble(8, v.getPrice());
            ps.setBoolean(9, v.isSold());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeVehicle(int vin) {
        String sql = "DELETE FROM vehicles WHERE Vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vin);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> extractVehicles(ResultSet rs) throws SQLException {
        List<Vehicle> list = new ArrayList<>();
        while (rs.next()) {
            Vehicle v = new Vehicle(
                    rs.getInt("Vin"),
                    rs.getString("VehicleMake"),
                    rs.getString("VehicleModel"),
                    rs.getString("Color"),
                    rs.getInt("VehicleYear"),
                    rs.getString("VehicleType"),
                    rs.getInt("Mileage"),
                    rs.getDouble("Price"),
                    rs.getBoolean("is_sold")
            );
            list.add(v);
        }
        return list;

    }
}





