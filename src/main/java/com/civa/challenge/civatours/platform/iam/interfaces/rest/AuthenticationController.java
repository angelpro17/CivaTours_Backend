package com.civa.challenge.civatours.platform.iam.interfaces.rest;

import com.civa.challenge.civatours.platform.iam.domain.services.UserCommandService;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.resources.SignInResource;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.resources.SignUpResource;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.resources.UserResource;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in", description = "Authenticate user and get JWT token", security = {})
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);

        var authenticatedUser = userCommandService.handle(signInCommand);

        if (authenticatedUser.isEmpty()) { return ResponseEntity.notFound().build(); }

        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler
            .toResourceFromEntity(
                authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());

        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Sign up", description = "Register a new user", security = {})
    public ResponseEntity<UserResource> signUp(@Valid @RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
