package com.example.mid_practice.service;


import com.example.mid_practice.dto.Reservations;
import com.example.mid_practice.dto.Rooms;
import com.example.mid_practice.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) { this.reservationRepository = reservationRepository; }

    public List<Reservations> findAll() {return reservationRepository.findAll();}

    public Reservations findById(long id){
        Reservations reservations = reservationRepository.findById(id);

        if(reservations == null){
            return null;
        }

        return reservations;
    }

    @Transactional
    public Reservations insertReservations(Reservations reservations) {

        if (reservations.getStartTime().isAfter(reservations.getEndTime())) {
            throw new IllegalArgumentException("開始時間は終了時間より前に設定してください。");
        }

        reservationRepository.insertReservations(reservations);
        return reservations;
    }

    @Transactional
    public Reservations updateReservation(Reservations reservations,long id){
        reservations.setId(id);

        return reservationRepository.updateReservation(reservations);
    }

    public boolean deleteReservation(long id){
        if(!reservationRepository.deleteReservation(id)){
            return false;
        }
        return true;
    }
}
