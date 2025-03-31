package com.moduledomain.command.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MessageService {
    public void send(String message) throws InterruptedException {
        Thread.sleep(500);
        log.info(message);
    }
}
