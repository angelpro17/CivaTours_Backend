package com.civa.challenge.civatours.platform.bus.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BusStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false, unique = true)
    private String statusName;
    
    @Column
    private String description;
    
    public BusStatus() {}
    
    public BusStatus(String statusName, String description) {
        this.statusName = statusName;
        this.description = description;
    }
}
