package com.civa.challenge.civatours.platform.bus.interfaces.rest.transform;

import com.civa.challenge.civatours.platform.bus.domain.model.commands.CreateBusCommand;
import com.civa.challenge.civatours.platform.bus.interfaces.rest.resources.CreateBusResource;

public class CreateBusCommandFromResourceAssembler {
    public static CreateBusCommand toCommandFromResource(CreateBusResource resource) {
        return new CreateBusCommand(
                resource.busNumber(),
                resource.licensePlate(),
                resource.characteristics(),
                resource.busBrand(),
                resource.isActive()
        );
    }
}
