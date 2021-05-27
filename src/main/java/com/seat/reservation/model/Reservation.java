package com.seat.reservation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservation")
public class Reservation {
  @Id
  private String id;

  private String email;
  private String userName;
  private boolean emailEnabled;
  private boolean published;
  private Driver driver;

  public Reservation() {
  }

  public Reservation(String email, String userName, boolean emailEnabled, Driver driver) {
    this.email = email;
    this.userName = userName;
    this.emailEnabled = emailEnabled;
    this.driver = driver;
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

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + email + ", desc=" + userName + ", emailEnabled=" + emailEnabled + "]";
  }
}
