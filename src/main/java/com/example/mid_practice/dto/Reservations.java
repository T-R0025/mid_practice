package com.example.mid_practice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
// JSONの出力順序を明示的に指定する
@JsonPropertyOrder({ "id", "roomName", "reservedUser", "startTime", "endTime" })
public class Reservations {
    private long id;
    private int roomId;
    private String roomName;
    private String reservedUser;
    // フォーマットを指定して T をスペースに変換する
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
