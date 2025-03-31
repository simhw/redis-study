package com.moduleinfra.repository.screening;

import com.moduledomain.command.domain.screnning.Genre;
import com.moduledomain.query.dto.*;
import com.moduleinfra.entity.screening.QMovieJpaEntity;
import com.moduleinfra.entity.screening.QScreeningJpaEntity;
import com.moduleinfra.entity.theater.QTheaterJpaEntity;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.moduleinfra.entity.screening.QMovieJpaEntity.movieJpaEntity;
import static com.moduleinfra.entity.screening.QScreeningJpaEntity.screeningJpaEntity;
import static com.moduleinfra.entity.theater.QTheaterJpaEntity.theaterJpaEntity;
import static org.springframework.util.StringUtils.*;

@Repository
@AllArgsConstructor
public class ScreeningQueryRepositoryImpl implements ScreeningQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    @Cacheable(cacheNames = "movies", cacheManager = "globalCacheManager",
            key = "'movies:' + #criteria.genre + ':' + #criteria.title")
    public List<ScreeningInfo> fetchActiveScreenings(FetchMovieCriteria criteria) {
        QScreeningJpaEntity screening = screeningJpaEntity;
        QMovieJpaEntity movie = movieJpaEntity;
        QTheaterJpaEntity theater = theaterJpaEntity;

        return queryFactory
                .select(
                        Projections.constructor(ScreeningInfo.class, // 상영
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
                .join(movie).on(screening.movieId.eq(movie.id))
                .join(theater).on(screening.theaterId.eq(theater.id))
                .where(
                        movieGenreEquals(criteria.getGenre()),
                        movieTitleEquals(criteria.getTitle()),
                        screening.startAt.after(LocalDateTime.now())
                )
                .orderBy(movie.releaseAt.desc())
                .fetch();
    }

    private Predicate movieTitleEquals(String title) {
        return hasText(title) ? movieJpaEntity.title.eq(title) : null;
    }

    private Predicate movieGenreEquals(Genre genre) {
        return genre != null ? movieJpaEntity.genre.eq(genre) : null;
    }

}
