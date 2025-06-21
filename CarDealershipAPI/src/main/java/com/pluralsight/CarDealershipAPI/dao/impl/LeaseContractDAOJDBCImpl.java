package com.pluralsight.CarDealershipAPI.dao.impl;

import com.pluralsight.CarDealershipAPI.dao.LeaseContractDao;
import com.pluralsight.CarDealershipAPI.model.LeaseContract;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeaseContractDAOJDBCImpl implements LeaseContractDao {
    private final DataSource dataSource;

    public LeaseContractDAOJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addLeaseContract(LeaseContract leaseContract) {
        String sql = "INSERT INTO lease_contracts (Vin, CustomerFullName, CustomerPhone, LeaseStartDate, LeaseEndDate, MonthlyPayment, MileageLimit, Deposit) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, leaseContract.getVin());
            ps.setString(2, leaseContract.getCustomerFullName());
            ps.setString(3, leaseContract.getCustomerPhone());
            ps.setTimestamp(4, Timestamp.valueOf(leaseContract.getLeaseStartDate()));
            ps.setTimestamp(5, Timestamp.valueOf(leaseContract.getLeaseEndDate()));
            ps.setBigDecimal(6, leaseContract.getMonthlyPayment());
            ps.setInt(7, leaseContract.getMileageLimit());
            ps.setBigDecimal(8, leaseContract.getDeposit());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LeaseContract findById(int id) {
        String sql = "SELECT * FROM lease_contracts WHERE LeaseContractID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new LeaseContract(
                        rs.getInt("LeaseContractID"),
                        rs.getString("Vin"),
                        rs.getString("CustomerFullName"),
                        rs.getString("CustomerPhone"),
                        rs.getTimestamp("LeaseStartDate").toLocalDateTime(),
                        rs.getTimestamp("LeaseEndDate").toLocalDateTime(),
                        rs.getBigDecimal("MonthlyPayment"),
                        rs.getInt("MileageLimit"),
                        rs.getBigDecimal("Deposit")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LeaseContract> getAllLeaseContracts() {
        String sql = "SELECT * FROM lease_contracts";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            return extractLeaseContracts(stmt.executeQuery(sql));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LeaseContract> extractLeaseContracts(ResultSet rs) throws SQLException {
        List<LeaseContract> list = new ArrayList<>();
        while (rs.next()) {
            LeaseContract lc = new LeaseContract(
                    rs.getInt("LeaseContractID"),
                    rs.getString("Vin"),
                    rs.getString("CustomerFullName"),
                    rs.getString("CustomerPhone"),
                    rs.getTimestamp("LeaseStartDate").toLocalDateTime(),
                    rs.getTimestamp("LeaseEndDate").toLocalDateTime(),
                    rs.getBigDecimal("MonthlyPayment"),
                    rs.getInt("MileageLimit"),
                    rs.getBigDecimal("Deposit")
            );
            list.add(lc);
        }
        return list;
    }

}
