package com.moduledomain.query.dto;

import java.util.List;

public interface ScreeningQueryRepository {
    List<ScreeningInfo> fetchByMovieReleaseAtDesc();
}
