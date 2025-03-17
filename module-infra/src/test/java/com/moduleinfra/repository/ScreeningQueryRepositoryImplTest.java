package com.moduleinfra.repository;


import com.moduledomain.query.dto.ScreeningInfo;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class ScreeningQueryRepositoryImplTest {

    @Autowired
    ScreeningQueryRepositoryImpl repository;

    @Test
    void getAllByMovieReleaseAtDesc() {
        List<ScreeningInfo> summaries = repository.getAllByMovieReleaseAtDesc();
        Assertions.assertThat(summaries).isNotEmpty();
    }
}