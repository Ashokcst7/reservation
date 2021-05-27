package com.seat.reservation.repository;

import java.util.List;

import com.seat.reservation.domain.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
  List<Reservation> findByPublished(boolean emailEnabled);
}
