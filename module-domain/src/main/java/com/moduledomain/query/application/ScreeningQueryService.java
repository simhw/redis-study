package com.moduledomain.query.application;

import com.moduledomain.query.dto.FetchMovieCriteria;
import com.moduledomain.query.dto.ScreeningInfo;
import com.moduledomain.query.dto.ScreeningQueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScreeningQueryService {
    private final ScreeningQueryRepository screeningQueryRepository;

    public List<ScreeningInfo> getActiveScreenings(FetchMovieCriteria criteria) {
        return screeningQueryRepository.fetchActiveScreenings(criteria);
    }
}
