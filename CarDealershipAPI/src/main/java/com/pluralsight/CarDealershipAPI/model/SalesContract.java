package com.pluralsight.CarDealershipAPI.model;

import java.math.BigDecimal;

public class SalesContract {
    private int id;
    private String vin;
    private String customerName;
    private String customerPhone;
    private BigDecimal salePrice;

    public SalesContract() {
    }

    public SalesContract(int id, String vin, String customerName, String customerPhone, BigDecimal salePrice) {
        this.id = id;
        this.vin = vin;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.salePrice = salePrice;
    }

    public SalesContract(String vin, String customerName, String customerPhone, BigDecimal salePrice) {
        this.vin = vin;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.salePrice = salePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return String.format(
                "ContractID[%d] VIN:%s | Customer:%s | Phone:%s | Price:%s",
                id,
                vin,
                customerName,
                customerPhone,
                salePrice
        );
    }

}
