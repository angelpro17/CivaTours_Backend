package com.civa.challenge.civatours.platform.iam.application.internal.eventhandlers;

import com.civa.challenge.civatours.platform.iam.domain.model.events.UserCreationEvent;
import com.civa.challenge.civatours.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.civa.challenge.civatours.platform.iam.domain.model.valueobjects.Roles;
import com.civa.challenge.civatours.platform.iam.domain.services.UserQueryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCreationEventHandler {
    private final UserQueryService userQueryService;
    private static final Logger logger = LoggerFactory.getLogger(UserCreationEventHandler.class);

    @EventListener(UserCreationEvent.class)
    public void on(UserCreationEvent event) {
        try {
            var user = userQueryService.handle(new GetUserByIdQuery(event.getUserId()));

            if (user.isEmpty()) {
                throw new RuntimeException("User not found with ID: " + event.getUserId());
            }

            var userEntity = user.get();

            if (userEntity.getRole() != null && userEntity.getRole().getName() == Roles.ADMIN_ROLE) {
                logger.info("Admin user created with ID: {}", userEntity.getId());
            }
        } catch (Exception e) {
            logger.error("Error handling user creation event for user ID {}: {}", event.getUserId(), e.getMessage(), e);
        }
    }
}
