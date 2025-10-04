package com.civa.challenge.civatours.platform.bus.interfaces.rest.transform;

import com.civa.challenge.civatours.platform.bus.domain.model.aggregates.Bus;
import com.civa.challenge.civatours.platform.bus.interfaces.rest.resources.BusResource;

public class BusResourceFromEntityAssembler {
    public static BusResource toResourceFromEntity(Bus entity) {
        return new BusResource(
                entity.getId(),
                entity.getBusNumber(),
                entity.getLicensePlate(),
                entity.getCharacteristics(),
                entity.getBusBrand(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
