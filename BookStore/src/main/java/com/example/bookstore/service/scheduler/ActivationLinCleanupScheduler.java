package com.example.bookstore.service.scheduler;

import com.example.bookstore.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;

public class ActivationLinCleanupScheduler {
    private final UserActivationService userActivationService;


    public ActivationLinCleanupScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanUp() {
        //System.out.println("Trigger cleanup of activation links. " + LocalDateTime.now());
        // TODO.
        userActivationService.cleanUpObsoleteActivationLinks();
    }
}
