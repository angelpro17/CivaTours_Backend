package com.civa.challenge.civatours.platform.bus.domain.services;

import com.civa.challenge.civatours.platform.bus.domain.model.aggregates.Bus;
import com.civa.challenge.civatours.platform.bus.domain.model.commands.ChangeBusStatusCommand;
import com.civa.challenge.civatours.platform.bus.domain.model.commands.CreateBusCommand;

import java.util.Optional;

public interface BusCommandService {
    Optional<Bus> handle(CreateBusCommand command);
    Optional<Bus> handle(ChangeBusStatusCommand command);
}
