package com.example.bookstore.service.impl;

import com.example.bookstore.model.entity.UserActivationCodeEntity;
import com.example.bookstore.model.event.UserRegisteredEvent;
import com.example.bookstore.repository.UserActivationCodeRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.EmailService;
import com.example.bookstore.service.UserActivationService;
import com.example.bookstore.service.exception.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private static final String ACTIVATION_CODE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789";
    private static final int ACTIVATION_CODE_LENGTH = 20;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserActivationCodeRepository userActivationCodeRepository;

    public UserActivationServiceImpl(EmailService emailService,
                                     UserRepository userRepository,
                                     UserActivationCodeRepository userActivationCodeRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    @Override
    public void userRegistered(UserRegisteredEvent event) {
        String activationCode = createActivationCode(event.getEmail());
        emailService.sendRegistrationEmail(event.getEmail(), event.getUsername(), activationCode);
    }


    @Override
    public String createActivationCode(String userEmail) {
        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();
        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUser(userRepository.findByEmail(userEmail).orElseThrow(() -> new ObjectNotFoundException("User not found :(")));

        userActivationCodeRepository.save(userActivationCodeEntity);

        return activationCode;
    }

    private String generateActivationCode() {
        StringBuilder activationCode = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randInx));
        }

        return activationCode.toString();
    }

    private Long getId(UserDetails userDetails){
        return userRepository.findByUsername(userDetails.getUsername()).orElseThrow().getId();
    }
}
