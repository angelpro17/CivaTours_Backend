package com.civa.challenge.civatours.platform.bus.application.internal.commandservices;

import com.civa.challenge.civatours.platform.bus.domain.model.aggregates.Bus;
import com.civa.challenge.civatours.platform.bus.domain.model.commands.ChangeBusStatusCommand;
import com.civa.challenge.civatours.platform.bus.domain.model.commands.CreateBusCommand;
import com.civa.challenge.civatours.platform.bus.domain.services.BusCommandService;
import com.civa.challenge.civatours.platform.bus.infrastructure.persistence.jpa.repositories.BusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BusCommandServiceImpl implements BusCommandService {
    private final BusRepository busRepository;

    @Override
    public Optional<Bus> handle(CreateBusCommand command) {
        if (busRepository.findByBusNumber(command.busNumber()).isPresent()) {
            throw new IllegalArgumentException("Bus with number " + command.busNumber() + " already exists.");
        }
        if (busRepository.findByLicensePlate(command.licensePlate()).isPresent()) {
            throw new IllegalArgumentException("Bus with license plate " + command.licensePlate() + " already exists.");
        }
        Bus bus = new Bus(command.busNumber(), command.licensePlate(), command.characteristics(), command.busBrand(), command.isActive());
        return Optional.of(busRepository.save(bus));
    }

    @Override
    public Optional<Bus> handle(ChangeBusStatusCommand command) {
        return busRepository.findById(command.busId())
                .map(bus -> {
                    bus.changeStatus(command.isActive());
                    return busRepository.save(bus);
                });
    }
}
