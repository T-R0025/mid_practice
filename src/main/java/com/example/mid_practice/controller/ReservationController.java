package com.example.mid_practice.controller;


import com.example.mid_practice.dto.Reservations;
import com.example.mid_practice.dto.Rooms;
import com.example.mid_practice.service.ReservationService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservations>> findAllRooms(){
        List<Reservations> reservations = reservationService.findAll();

        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservations> findById(@PathVariable Long id){
        Reservations reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    public ResponseEntity<Reservations> insertReservations(@RequestBody Reservations reservations,
                                                           UriComponentsBuilder uriBuilder){

        Reservations createdReservation = reservationService.insertReservations(reservations);

        URI location = uriBuilder.path("/api/reservation/{id}")
                .buildAndExpand(createdReservation.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.created(location).body(createdReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservations> updateReservation(@PathVariable long id,
                                                          @RequestBody Reservations reservations){
        reservations.setId(id);

        reservationService.updateReservation(reservations,id);

        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteReservation(@PathVariable long id){
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }


}
