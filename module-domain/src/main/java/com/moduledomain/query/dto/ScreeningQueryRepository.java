package com.moduledomain.query.dto;

import java.util.List;

public interface ScreeningQueryRepository {
    public List<ScreeningInfo> getAllByMovieReleaseAtDesc();
}
