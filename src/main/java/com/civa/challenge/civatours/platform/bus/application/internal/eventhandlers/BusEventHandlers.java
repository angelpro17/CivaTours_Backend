package com.civa.challenge.civatours.platform.bus.application.internal.eventhandlers;

import com.civa.challenge.civatours.platform.bus.domain.model.events.BusCreatedEvent;
import com.civa.challenge.civatours.platform.bus.domain.model.events.BusStatusChangedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusEventHandlers {

    @EventListener
    public void handle(BusCreatedEvent event) {
        log.info("Bus created: ID={}, Number={}, License={}, Brand={}", 
                event.getBusId(), 
                event.getBusNumber(), 
                event.getLicensePlate(), 
                event.getBusBrand());
    }

    @EventListener
    public void handle(BusStatusChangedEvent event) {
        log.info("Bus status changed: ID={}, Number={}, From={} to {}", 
                event.getBusId(), 
                event.getBusNumber(), 
                event.getPreviousStatus(), 
                event.getNewStatus());
    }
}
