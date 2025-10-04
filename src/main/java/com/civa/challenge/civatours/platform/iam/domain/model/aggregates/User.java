package com.civa.challenge.civatours.platform.iam.domain.model.aggregates;

import com.civa.challenge.civatours.platform.iam.domain.model.entities.Role;
import com.civa.challenge.civatours.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends AuditableAbstractAggregateRoot<User> {
    
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    public User() { }

    public User(String email, String password, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(String email, String password, String firstName, String lastName, String phoneNumber, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getUsername() {
        return email;
    }

    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean isValidPassword() {
        return password != null && password.length() >= 6;
    }

    public boolean isValidPhoneNumber() {
        return phoneNumber != null && phoneNumber.matches("^\\d{3} \\d{3} \\d{3}$");
    }

    public boolean isValidUser() {
        return isValidEmail() && isValidPassword() && isValidPhoneNumber() 
               && firstName != null && !firstName.trim().isEmpty()
               && lastName != null && !lastName.trim().isEmpty();
    }
}
