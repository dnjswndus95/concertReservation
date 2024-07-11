package com.example.hhpuls.concertReservation.application.service;

import com.example.hhpuls.concertReservation.application.command.ReservationCommand;
import com.example.hhpuls.concertReservation.application.model.ReservationInfoWithSeatInfoModel;
import com.example.hhpuls.concertReservation.domain.domain.Reservation;

public interface ReservationService {

    public ReservationInfoWithSeatInfoModel reserve(ReservationCommand.createCommand command);

    public Reservation findById(Long id);

    public Reservation cancelReserve(Long id);
}
