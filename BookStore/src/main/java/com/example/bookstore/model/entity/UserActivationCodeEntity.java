package com.example.bookstore.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "user_activation_codes")
public class UserActivationCodeEntity extends BaseEntity {

    private String activationCode;

    private Instant created;

    private User user;

    public String getActivationCode() {
        return activationCode;
    }

    public UserActivationCodeEntity setActivationCode(String activationCode) {
        this.activationCode = activationCode;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public UserActivationCodeEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public UserActivationCodeEntity setUser(User user) {
        this.user = user;
        return this;
    }
}