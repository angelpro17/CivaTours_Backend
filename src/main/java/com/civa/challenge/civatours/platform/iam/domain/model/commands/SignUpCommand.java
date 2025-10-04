package com.civa.challenge.civatours.platform.iam.domain.model.commands;

public record SignUpCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String phoneNumber) {
}
