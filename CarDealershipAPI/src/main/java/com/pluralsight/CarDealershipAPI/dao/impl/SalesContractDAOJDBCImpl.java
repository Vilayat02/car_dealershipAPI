package com.pluralsight.CarDealershipAPI.dao.impl;

import com.pluralsight.CarDealershipAPI.dao.SalesContractDAO;
import com.pluralsight.CarDealershipAPI.model.SalesContract;
import com.pluralsight.CarDealershipAPI.model.Vehicle;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalesContractDAOJDBCImpl implements SalesContractDAO {

    private DataSource dataSource;

    public SalesContractDAOJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SalesContract findById(int id) {
        String sql = "SELECT * FROM sales_contracts WHERE ContractID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new SalesContract(
                        rs.getInt("ContractID"),
                        rs.getString("Vin"),
                        rs.getString("CustomerName"),
                        rs.getString("CustomerPhone"),
                        rs.getBigDecimal("SalePrice"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addSalesContract(SalesContract salesContract) {
        String sql = "INSERT INTO sales_contracts (Vin, CustomerName, CustomerPhone, SalePrice) VALUES (?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, salesContract.getVin());
            ps.setString(2, salesContract.getCustomerName());
            ps.setString(3, salesContract.getCustomerPhone());
            ps.setBigDecimal(4, salesContract.getSalePrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SalesContract> getAllSalesContract() {
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement()) {
            return extractSalesContract(st.executeQuery("SELECT * FROM sales_contracts"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SalesContract> extractSalesContract(ResultSet rs) throws SQLException {
        List<SalesContract> list = new ArrayList<>();
        while (rs.next()) {
            SalesContract salesContract = new SalesContract(
                    rs.getInt("ContractID"),
                    rs.getString("Vin"),
                    rs.getString("CustomerName"),
                    rs.getString("CustomerPhone"),
                    rs.getBigDecimal("SalePrice"));
            list.add(salesContract);
        }
        return list;

    }

}
