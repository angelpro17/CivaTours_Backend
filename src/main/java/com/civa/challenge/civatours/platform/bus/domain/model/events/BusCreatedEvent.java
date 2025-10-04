package com.civa.challenge.civatours.platform.bus.domain.model.events;

import java.util.UUID;

public class BusCreatedEvent {
    private final UUID busId;
    private final String busNumber;
    private final String licensePlate;
    private final String busBrand;
    private final long timestamp;

    public BusCreatedEvent(UUID busId, String busNumber, String licensePlate, String busBrand) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.licensePlate = licensePlate;
        this.busBrand = busBrand;
        this.timestamp = System.currentTimeMillis();
    }

    public UUID getBusId() {
        return busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBusBrand() {
        return busBrand;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
