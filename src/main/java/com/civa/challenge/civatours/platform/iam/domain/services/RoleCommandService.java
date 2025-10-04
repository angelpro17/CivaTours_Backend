package com.civa.challenge.civatours.platform.iam.domain.services;

import com.civa.challenge.civatours.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
