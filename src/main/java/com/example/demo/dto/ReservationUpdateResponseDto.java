package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationUpdateResponseDto {
    private String status;

    @JsonCreator
    public ReservationUpdateResponseDto(String status) {
        this.status = status;
    }
}
