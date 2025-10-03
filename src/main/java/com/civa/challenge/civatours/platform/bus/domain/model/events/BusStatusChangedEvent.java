package com.civa.challenge.civatours.platform.bus.domain.model.events;

import java.util.UUID;

public class BusStatusChangedEvent {
    private final UUID busId;
    private final String busNumber;
    private final boolean previousStatus;
    private final boolean newStatus;
    private final long timestamp;

    public BusStatusChangedEvent(UUID busId, String busNumber, boolean previousStatus, boolean newStatus) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.previousStatus = previousStatus;
        this.newStatus = newStatus;
        this.timestamp = System.currentTimeMillis();
    }

    public UUID getBusId() {
        return busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public boolean getPreviousStatus() {
        return previousStatus;
    }

    public boolean getNewStatus() {
        return newStatus;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
