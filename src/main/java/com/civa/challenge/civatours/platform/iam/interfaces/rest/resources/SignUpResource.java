package com.civa.challenge.civatours.platform.iam.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpResource(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid (contain @ and .)")
        @Schema(example = "string")
        String email,
        
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        @Schema(example = "string")
        String password,
        
        @NotBlank(message = "First name is required")
        @Schema(example = "string")
        String firstName,
        
        @NotBlank(message = "Last name is required")
        @Schema(example = "string")
        String lastName,
        
        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\d{9}$", message = "Phone number must be exactly 9 digits")
        @Schema(example = "string")
        String phoneNumber
) {
}

