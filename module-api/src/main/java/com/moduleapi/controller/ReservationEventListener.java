package com.moduleapi.controller;

import com.moduledomain.command.domain.reservation.ReservedEvent;
import com.moduledomain.command.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class ReservationEventListener {
    private final MessageService messageService;

    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void reserved(ReservedEvent event) throws InterruptedException {
        messageService.send(event.getId() + "번 예약이 완료되었습니다.");
    }
}
