package com.civa.challenge.civatours.platform.iam.interfaces.rest.resources;

import java.util.List;
import java.util.UUID;

public record UserResource(
        UUID id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        List<String> roles
) {
}
