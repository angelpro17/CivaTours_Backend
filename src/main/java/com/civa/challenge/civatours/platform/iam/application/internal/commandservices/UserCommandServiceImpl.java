package com.civa.challenge.civatours.platform.iam.application.internal.commandservices;

import com.civa.challenge.civatours.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.civa.challenge.civatours.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.civa.challenge.civatours.platform.iam.domain.model.aggregates.User;
import com.civa.challenge.civatours.platform.iam.domain.model.commands.SignInCommand;
import com.civa.challenge.civatours.platform.iam.domain.model.commands.SignUpCommand;
import com.civa.challenge.civatours.platform.iam.domain.model.events.UserCreationEvent;
import com.civa.challenge.civatours.platform.iam.domain.model.valueobjects.Roles;
import com.civa.challenge.civatours.platform.iam.domain.services.UserCommandService;
import com.civa.challenge.civatours.platform.iam.infrastructure.persistance.jpa.repositories.RoleRepository;
import com.civa.challenge.civatours.platform.iam.infrastructure.persistance.jpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final ApplicationEventPublisher eventPublisher;
    private static final Logger logger = LoggerFactory.getLogger(UserCommandServiceImpl.class);

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty())
            throw new RuntimeException("Email not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email()))
            throw new RuntimeException("Email already exists");
        // Get ADMIN_ROLE as default role
        var adminRole = roleRepository.findByName(Roles.ADMIN_ROLE)
                .orElseThrow(() -> new RuntimeException("ADMIN_ROLE not found"));

        User user = new User(
                command.email(),
                hashingService.encode(command.password()),
                command.firstName(),
                command.lastName(),
                command.phoneNumber(),
                adminRole
        );

        var savedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreationEvent(savedUser, savedUser.getId()));
        return userRepository.findByEmail(command.email());
    }
}
