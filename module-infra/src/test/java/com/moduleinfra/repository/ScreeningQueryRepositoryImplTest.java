package com.moduleinfra.repository;


import com.moduledomain.query.dto.ScreeningInfo;
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
    void fetchByMovieReleaseAtDesc() {
        List<ScreeningInfo> summaries = repository.fetchByMovieReleaseAtDesc();
        Assertions.assertThat(summaries).isNotEmpty();
    }
}