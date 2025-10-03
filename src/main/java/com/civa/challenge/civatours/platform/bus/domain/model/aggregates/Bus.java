package com.civa.challenge.civatours.platform.bus.domain.model.aggregates;

import com.civa.challenge.civatours.platform.bus.domain.model.events.BusCreatedEvent;
import com.civa.challenge.civatours.platform.bus.domain.model.events.BusStatusChangedEvent;
import com.civa.challenge.civatours.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bus extends AuditableAbstractAggregateRoot<Bus> {

    @Column(nullable = false, unique = true)
    private String busNumber;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private String characteristics;

    @Column(nullable = false)
    private String busBrand;

    @Column(nullable = false)
    private Boolean isActive;

    public Bus() {
        this.isActive = true; // Default to active
    }

    public Bus(String busNumber, String licensePlate, String characteristics, String busBrand) {
        this(); // Call default constructor to set isActive
        this.busNumber = busNumber;
        this.licensePlate = licensePlate;
        this.characteristics = characteristics;
        this.busBrand = busBrand;
        
        // Publish domain event when bus is created
        this.registerEvent(new BusCreatedEvent(this.getId(), this.busNumber, this.licensePlate, this.busBrand));
    }
    
    public Bus(String busNumber, String licensePlate, String characteristics, String busBrand, Boolean isActive) {
        this.busNumber = busNumber;
        this.licensePlate = licensePlate;
        this.characteristics = characteristics;
        this.busBrand = busBrand;
        this.isActive = isActive != null ? isActive : true; // Default to true if null
        
        // Publish domain event when bus is created
        this.registerEvent(new BusCreatedEvent(this.getId(), this.busNumber, this.licensePlate, this.busBrand));
    }
    
    /**
     * Changes the active status of the bus
     * @param newStatus the new active status
     */
    public void changeStatus(boolean newStatus) {
        boolean previousStatus = this.isActive;
        this.isActive = newStatus;
        
        // Publish domain event when status changes
        this.registerEvent(new BusStatusChangedEvent(this.getId(), this.busNumber, previousStatus, newStatus));
    }
}
