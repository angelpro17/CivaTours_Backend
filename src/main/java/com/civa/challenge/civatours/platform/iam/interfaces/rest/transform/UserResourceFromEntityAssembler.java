package com.civa.challenge.civatours.platform.iam.interfaces.rest.transform;

import com.civa.challenge.civatours.platform.iam.domain.model.aggregates.User;
import com.civa.challenge.civatours.platform.iam.domain.model.entities.Role;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRole() != null ? 
                java.util.List.<String>of(user.getRole().getStringName()) :
                java.util.List.<String>of();

        return new UserResource(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                roles
        );
    }
}
