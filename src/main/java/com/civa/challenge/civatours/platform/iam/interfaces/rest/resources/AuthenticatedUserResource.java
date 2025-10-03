package com.civa.challenge.civatours.platform.iam.interfaces.rest.resources;

import java.util.List;
import java.util.UUID;

public record AuthenticatedUserResource(
        UUID id,
        String email,
        String token,
        List<String> roles
) {
}
