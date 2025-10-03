package com.civa.challenge.civatours.platform.bus.interfaces.rest.resources;

import java.util.Date;
import java.util.UUID;

public record BusResource(
        UUID id,
        String busNumber,
        String licensePlate,
        String characteristics,
        String busBrand,
        Boolean isActive,
        Date createdAt,
        Date updatedAt
) {
}
