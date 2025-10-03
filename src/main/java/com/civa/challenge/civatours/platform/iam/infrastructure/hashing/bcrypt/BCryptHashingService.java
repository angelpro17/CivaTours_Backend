package com.civa.challenge.civatours.platform.iam.infrastructure.hashing.bcrypt;

import com.civa.challenge.civatours.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
