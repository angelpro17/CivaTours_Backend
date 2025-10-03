package com.civa.challenge.civatours.platform.bus.domain.model.commands;

public record CreateBusCommand(
        String busNumber,
        String licensePlate,
        String characteristics,
        String busBrand,
        Boolean isActive
) {
}
