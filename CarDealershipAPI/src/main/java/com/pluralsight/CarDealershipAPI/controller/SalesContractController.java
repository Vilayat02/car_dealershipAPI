package com.pluralsight.CarDealershipAPI.controller;

import com.pluralsight.CarDealershipAPI.dao.SalesContractDAO;
import com.pluralsight.CarDealershipAPI.model.SalesContract;
import com.pluralsight.CarDealershipAPI.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales_contracts")
public class SalesContractController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SalesContract> getAllContracts() {
        return salesContractDAO.getAllSalesContract();
    }

    @Autowired
    private SalesContractDAO salesContractDAO;

    // POST /vehicles
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addSalesContract(@RequestBody SalesContract salesContract) {
        salesContractDAO.addSalesContract(salesContract);
    }

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public SalesContract getSalesContractById(@RequestParam int id) {
        return salesContractDAO.findById(id);
    }


}
