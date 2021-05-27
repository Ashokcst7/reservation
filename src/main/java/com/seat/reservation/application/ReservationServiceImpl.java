package com.seat.reservation.application;

import com.seat.reservation.application.service.MailServiceImpl;
import com.seat.reservation.domain.Reservation;
import com.seat.reservation.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReservationServiceImpl {


    /** The  email service. */
    @Autowired
    MailServiceImpl mailService;

    @Autowired
    ReservationRepository reservationRepository;

    private Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);
    /**
     * Send caregiver, pending help cancel email.
     *
     * @param id      		the id
     * @return the string
     */
    public boolean sendUserReservationEmail(String id) {

        boolean isEmailSend = false;
        Reservation reservationData = reservationRepository.findById(id).isPresent() ? reservationRepository.findById(id).get() : null;

        /** send email */
        try {
            if(null != reservationData){
                String userName = reservationData.getUserName();
                String driverName = reservationData.getDriverInfo().getName();

                String UserMailBody= String.join(
                        System.getProperty("line.separator"), driverName + " has confirmed the " + reservationData.getVehicle().getType()
                                + " requested by " + userName);
                    mailService.sendMail(reservationData.getEmail(), UserMailBody, "Reservation confirmation");

                String driverMailBody= String.join(
                        System.getProperty("line.separator"), driverName + " has confirmed the " + reservationData.getVehicle().getType()
                                + " requested by " + userName);

                mailService.sendMail(reservationData.getDriverInfo().getEmail(), driverMailBody, "Reservation confirmation");

                isEmailSend=true;
            }


        } catch (Exception e) {
            logger.error("exception while sending reservation email", e);
        }
        return isEmailSend;
    }


    /**
     * create User Reservation.
     *
     * @param reservation      		the reservation
     * @return the string
     */
    public void createUserReservation(Reservation reservation) {

        try {
            if (null != reservation) {
                Reservation reservationData = reservationRepository.save(new Reservation(reservation.getEmail(), reservation.getUserName(), true,
                        reservation.getDriverInfo(), reservation.getVehicle()));
            }
            logger.debug("Successfully saved...");
        } catch (Exception e) {
            logger.error("exception while creating reservation", e);
        }
    }


}
