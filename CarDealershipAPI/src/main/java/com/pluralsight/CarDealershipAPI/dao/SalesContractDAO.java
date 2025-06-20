package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.model.SalesContract;
import com.pluralsight.CarDealershipAPI.model.Vehicle;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SalesContractDAO {
    public SalesContract findById(int id);
    public void addSalesContract(SalesContract salesContract);
    public List<SalesContract> getAllSalesContract();
    public List<SalesContract>  extractSalesContract(ResultSet rs) throws SQLException;
}
