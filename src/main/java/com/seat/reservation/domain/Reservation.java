package com.seat.reservation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservation")
public class Reservation {
  @Id
  private String id;

  private String email;

  private String userName;

  private boolean emailEnabled;

  private DriverInfo driverInfo;

  private Vehicle vehicle;

  private boolean published;

  public Reservation() {
  }

  public Reservation(String email, String userName, boolean emailEnabled, DriverInfo driverInfo, Vehicle vehicle) {
    this.email = email;
    this.userName = userName;
    this.emailEnabled = emailEnabled;
    this.driverInfo = driverInfo;
    this.vehicle = vehicle;
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public boolean isEmailEnabled() {
    return emailEnabled;
  }

  public void setEmailEnabled(boolean emailEnabled) {
    this.emailEnabled = emailEnabled;
  }

  public DriverInfo getDriverInfo() {
    return driverInfo;
  }

  public void setDriverInfo(DriverInfo driverInfo) {
    this.driverInfo = driverInfo;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + email + ", desc=" + userName + ", emailEnabled=" + emailEnabled + "]";
  }
}
