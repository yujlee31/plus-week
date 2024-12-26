package com.example.demo.entity;

import lombok.Getter;

@Getter
public enum Status {
    PENDING("pending"),
    APPROVED("approved"),
    CANCELED("canceled"),
    EXPIRED("expired"),
    BLOCKED("blocked");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public static Status of(String statusName) {
        for (Status status : values()) {
            if (status.getName().equals(statusName)) {
                return status;
            }
        }

        throw new IllegalArgumentException("해당하는 이름의 상태를 찾을 수 없습니다: " + statusName);
    }
}
