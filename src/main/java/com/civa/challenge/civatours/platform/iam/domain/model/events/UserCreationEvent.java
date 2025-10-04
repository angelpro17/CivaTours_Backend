package com.civa.challenge.civatours.platform.iam.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class UserCreationEvent extends ApplicationEvent {
    private final UUID userId;

    public UserCreationEvent(Object source, UUID userId) {
        super(source);
        this.userId = userId;
    }
}
