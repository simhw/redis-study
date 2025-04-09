package com.moduleapi.controller;

import com.moduleapi.dto.GetMovieDto;
import com.moduleinfra.exception.SystemException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieControllerTest {

    @Autowired
    MovieController movieController;

    @Test
    @DisplayName("51번 이상 요청할 경우 SystemException 예외를 반환한다.")
    void 영화_조회() {
        // given
        GetMovieDto.SearchCondition condition = new GetMovieDto.SearchCondition();
        String ip = "127.0.0.2";

        for (int i = 0; i < 50; i++) {
            movieController.getNowShowingMovies(condition, ip);
        }

        // when, then
        // 51번째 호출 시 예외 발생 확인
        assertThrows(SystemException.class,
                () -> movieController.getNowShowingMovies(condition, ip));
    }
}