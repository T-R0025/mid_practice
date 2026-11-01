package com.example.mid_practice.repository;

import com.example.mid_practice.dto.Reservations;
import com.example.mid_practice.dto.Rooms;
import com.example.mid_practice.mapper.ReservationMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ReservationRepository {

    private final ReservationMapper reservationMapper;

    public ReservationRepository(ReservationMapper reservationMapper) { this.reservationMapper = reservationMapper; }

    public List<Reservations> findAll() { return reservationMapper.findAll(); }

    public Reservations findById(long id) { return reservationMapper.findById(id); }

    public void insertReservations(Reservations reservations) {
        reservationMapper.insertReservations(reservations);
    }

    public Reservations updateReservation(Reservations reservations){
        reservationMapper.updateReservation(reservations);
        return reservations;
    }

    public boolean deleteReservation(long id){return reservationMapper.deleteReservation(id) == 1;}
}
