package com.civa.challenge.civatours.platform.bus.domain.model.commands;

import java.util.UUID;

public record ChangeBusStatusCommand(
        UUID busId,
        boolean isActive
) {
}
