package com.civa.challenge.civatours.platform.iam.interfaces.rest.resources;

public record CreateUserResource(
        String email,
        String passwordHash
) {
}
