package com.civa.challenge.civatours.platform.iam.domain.model.queries;

import com.civa.challenge.civatours.platform.iam.domain.model.valueobjects.Roles;

public record GetAllUsersByRoleQuery(Roles role) {
}
