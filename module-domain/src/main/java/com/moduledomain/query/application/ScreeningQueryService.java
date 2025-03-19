package com.moduledomain.query.application;


import com.moduledomain.query.dto.ScreeningQueryRepository;
import com.moduledomain.query.dto.ScreeningInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScreeningQueryService {
    private final ScreeningQueryRepository screeningQueryRepository;

    public List<ScreeningInfo> getActiveScreenings() {
        return screeningQueryRepository.fetchByMovieReleaseAtDesc();
    }
}
