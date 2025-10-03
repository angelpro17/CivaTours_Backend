package com.civa.challenge.civatours.platform.iam.interfaces.rest.resources;

public record SignInResource(
        String email,
        String password
) {
}
