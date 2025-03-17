package com.moduleinfra.repository;


import com.moduleinfra.entity.ScreeningJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ScreeningQueryRepository {

    private final ScreeningJpaRepository screeningJpaRepository;

    public Iterable<ScreeningJpaEntity> findAll() {
        return screeningJpaRepository.findAll();
    }
}
