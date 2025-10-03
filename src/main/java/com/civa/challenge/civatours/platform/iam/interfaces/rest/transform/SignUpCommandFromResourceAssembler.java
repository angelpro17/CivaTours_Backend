package com.civa.challenge.civatours.platform.iam.interfaces.rest.transform;

import com.civa.challenge.civatours.platform.iam.domain.model.commands.SignUpCommand;
import com.civa.challenge.civatours.platform.iam.domain.model.entities.Role;
import com.civa.challenge.civatours.platform.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
  public static SignUpCommand toCommandFromResource(SignUpResource resource) {
    return new SignUpCommand(
            resource.email(),
            resource.password(),
            resource.firstName(),
            resource.lastName(),
            resource.phoneNumber()
    );
  }
}
