package com.modulecommon.exception;

public enum ErrorCode {
    // 400번대
    VALIDATION_ERROR,     // 필드 검증, 파라미터 오류
    UNAUTHORIZED,         // 인증 실패 (예: 로그인 필요)
    FORBIDDEN,            // 권한 없음
    RESOURCE_NOT_FOUND,   // 자원 없음
    CONFLICT,             // 중복 등 리소스 충돌

    // 500번대
    INTERNAL_ERROR,       // 처리 중 예상치 못한 서버 에러
    DATABASE_ERROR,       // DB 연산 실패
    REDIS_ERROR,          // Redis 실패
    TIMEOUT,              // 처리 시간 초과

    // 그 외 시스템
    TOO_MANY_REQUESTS,    // 과도한 요청 (rate limit)
    SERVICE_UNAVAILABLE   // 일시적 서비스 중단
}
