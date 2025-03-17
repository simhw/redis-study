package com.moduleinfra.entity;

import com.moduledomain.command.domain.Genre;
import com.moduledomain.command.domain.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "movie")
@AllArgsConstructor
public class MovieJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    private String title;
    private String thumbnail;
    private Integer runtime;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @Enumerated(value = EnumType.STRING)
    private Rating rating;
    private LocalDateTime releaseAt;

    protected MovieJpaEntity() {
    }
}
