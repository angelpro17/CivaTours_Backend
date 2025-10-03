package com.civa.challenge.civatours.platform.bus.interfaces.rest.resources;

public record CreateBusResource(
        String busNumber,
        String licensePlate,
        String characteristics,
        String busBrand,
        Boolean isActive
) {
}
