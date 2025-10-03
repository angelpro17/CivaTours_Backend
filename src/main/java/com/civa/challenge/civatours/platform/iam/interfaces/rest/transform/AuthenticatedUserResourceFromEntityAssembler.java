package com.civa.challenge.civatours.platform.iam.interfaces.rest.transform;

import com.civa.challenge.civatours.platform.iam.domain.model.aggregates.User;
import com.civa.challenge.civatours.platform.iam.domain.model.entities.Role;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

import java.util.List;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        List<String> roles = user.getRole() != null ? 
                java.util.List.of(user.getRole().getStringName()) :
                java.util.List.of();
        
        return new AuthenticatedUserResource(
                user.getId(),
                user.getUsername(),
                token,
                roles
        );
    }
}

