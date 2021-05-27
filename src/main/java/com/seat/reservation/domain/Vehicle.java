package com.seat.reservation.domain;

public class Vehicle {

    private String regNo;
    private VehicleType type;

    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }


}
