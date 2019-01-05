package com.psg.carparkmanager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {

    private String licensePlate;

    private Long ticket;

    private int space;
}
