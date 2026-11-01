package com.example.mid_practice.mapper;


import com.example.mid_practice.dto.Reservations;
import com.example.mid_practice.dto.Rooms;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReservationMapper {
    @Select("""
        SELECT 
            r.id AS id,
            r.room_id AS roomId,
            rm.room_name AS roomName,
            r.reserved_user AS reservedUser,
            r.start_time AS startTime,
            r.end_time AS endTime
        FROM reservations r
        INNER JOIN rooms rm ON r.room_id = rm.id
        ORDER BY r.id ASC
    """)
    List<Reservations> findAll();


    // 1件取得
    @Select("""
        SELECT 
            r.id AS id,
            r.room_id AS roomId,
            rm.room_name AS roomName,
            r.reserved_user AS reservedUser,
            r.start_time AS startTime,
            r.end_time AS endTime
        FROM reservations r
        INNER JOIN rooms rm ON r.room_id = rm.id
        WHERE r.id = #{id}
    """)
    Reservations findById(long id);

    @Insert("""
        INSERT INTO reservations (room_id, reserved_user, start_time, end_time)
        VALUES (#{roomId}, #{reservedUser}, #{startTime}, #{endTime})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertReservations(Reservations reservations);

    @Update("""
        UPDATE reservations
        SET room_id = #{roomId},
            reserved_user = #{reservedUser},
            start_time = #{startTime},
            end_time = #{endTime}
        WHERE id = #{id}
    """)
    int updateReservation(Reservations reservations);

    @Delete("DELETE FROM reservations WHERE id = #{id}")
    int deleteReservation(long id);
}
