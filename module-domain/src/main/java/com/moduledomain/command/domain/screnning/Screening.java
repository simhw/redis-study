package com.moduledomain.command.domain.screnning;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Screening {
    private Long id;
    private Long movieId;
    private Long theaterId;
    private Integer price;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Builder
    public Screening(Long movieId, Long theaterId, Integer price, LocalDateTime startAt, LocalDateTime endAt) {
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.price = price;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public void verifyIsNotYetStart() {
        if (this.startAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("이미 상영이 시작되었습니다.");
        }
    }
}
