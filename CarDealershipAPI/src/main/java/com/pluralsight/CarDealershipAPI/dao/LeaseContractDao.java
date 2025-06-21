package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.model.LeaseContract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface LeaseContractDao {

    void addLeaseContract(LeaseContract leaseContract);
    LeaseContract findById(int id);
    List<LeaseContract> getAllLeaseContracts();
    List<LeaseContract> extractLeaseContracts(ResultSet rs) throws SQLException;
}
