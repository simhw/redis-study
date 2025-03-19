package com.moduleinfra.repository;

import com.moduledomain.query.dto.MovieSummary;
import com.moduledomain.query.dto.ScreeningQueryRepository;
import com.moduledomain.query.dto.ScreeningInfo;
import com.moduledomain.query.dto.TheaterSummary;
import com.moduleinfra.entity.QMovieJpaEntity;
import com.moduleinfra.entity.QScreeningJpaEntity;
import com.moduleinfra.entity.QTheaterJpaEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.moduleinfra.entity.QMovieJpaEntity.*;
import static com.moduleinfra.entity.QScreeningJpaEntity.*;
import static com.moduleinfra.entity.QTheaterJpaEntity.*;

@Repository
@AllArgsConstructor
public class ScreeningQueryRepositoryImpl implements ScreeningQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScreeningInfo> fetchByMovieReleaseAtDesc() {
        QScreeningJpaEntity screening = screeningJpaEntity;
        QMovieJpaEntity movie = movieJpaEntity;
        QTheaterJpaEntity theater = theaterJpaEntity;

        return queryFactory
                .select(Projections.constructor(ScreeningInfo.class, // 상영
                        screening.id,
                        screening.startAt,
                        screening.endAt,
                        Projections.constructor(MovieSummary.class, // 영화
                                movie.id,
                                movie.title,
                                movie.rating,
                                movie.releaseAt,
                                movie.thumbnail,
                                movie.runtime,
                                movie.genre
                        ),
                        Projections.constructor(TheaterSummary.class,   // 상영관
                                theater.id,
                                theater.name
                        )
                ))
                .from(screening)
                .join(movie).on(screening.movie.eq(movie.id))
                .join(theater).on(screening.theater.eq(theater.id))
                .where(screening.startAt.after(LocalDateTime.now()))
                .orderBy(movie.releaseAt.desc())
                .fetch();
    }
}
