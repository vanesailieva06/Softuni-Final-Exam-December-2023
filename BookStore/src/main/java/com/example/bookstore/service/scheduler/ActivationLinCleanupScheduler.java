package com.example.bookstore.service.scheduler;

import com.example.bookstore.repository.BookRepository;
import org.springframework.scheduling.annotation.Scheduled;

public class ActivationLinCleanupScheduler {
    private final BookRepository bookRepository;


    public ActivationLinCleanupScheduler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanUp() {
        System.out.printf("There are %d books in the database.", bookRepository.count());
    }
}
