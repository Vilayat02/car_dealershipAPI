package com.pluralsight.CarDealershipAPI.controller;

import com.pluralsight.CarDealershipAPI.dao.LeaseContractDao;
import com.pluralsight.CarDealershipAPI.model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lease_contracts")
public class LeaseContractController {
    @Autowired
    private LeaseContractDao leaseContractDAO;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LeaseContract> getAll() {
        return leaseContractDAO.getAllLeaseContracts();
    }

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public LeaseContract getByQueryId(@RequestParam int id) {
        return leaseContractDAO.findById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeaseContract getByPathId(@PathVariable int id) {
        return leaseContractDAO.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody LeaseContract contract) {
        leaseContractDAO.addLeaseContract(contract);
    }
}
