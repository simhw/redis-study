# Rrdis Proejct

## 영화 예매 시스템

### 1. 멀티 모듈

#### 1. API 모듈(module-module)

- 클라이언트와 직접 통신하는 Controller 계층 포함
- 요청을 받아 도메인 계층에 위임하고 응답을 반환

#### 2. 도메인 모듈(domain-module)

- 비즈니스 핵심 규칙을 구현하는 Domain 계층 포함
- 외부 시스템에 대한 의존성을 제거하여 확장성을 높임

#### 3. 인프라 모듈(infra-module)

- 외부 시스템과의 연결 담당하는 Repository 계층 포함
- 도메인 모듈에서 사용하는 구체적인 구현체를 제공

```mermaid
graph TD
    1[API 모듈] --> 2(도메인 모듈)
    2 --> 3(인프라 모듈)
    1 --> 3
```

### 2. ERD

```mermaid
erDiagram
    MOVIE {
        bigint movie_id PK "AUTO_INCREMENT, 영화 ID"
        varchar(255) title "영화 제목"
        varchar(255) thumbnail "썸네일 URL"
        int runtime "상영 시간(분)"
        varchar(255) genre "장르(SF, COMEDY, ACTION 등)"
        varchar(255) rating "영상물 등급(G, R12, R15, R18)"
        datetime(6) release_at "개봉일"
        datetime(6) created_at "생성일"
        datetime(6) updated_at "수정일"
        datetime(6) deleted_at "삭제일"
    }

    SCREENING {
        bigint screening_id PK "AUTO_INCREMENT, 상영 ID"
        bigint movie_id FK "영화 ID)"
        datetime(6) start_at "상영 시작 시간"
        datetime(6) end_at "상영 종료 시간"
        bigint theater_id FK "상영관 ID"
        datetime(6) created_at "생성일"
        datetime(6) updated_at "수정일"
        datetime(6) deleted_at "삭제일"
    }

    THEATER {
        bigint theater_id PK "AUTO_INCREMENT, 상영관 ID"
        varchar(255) name "상영관 이름"
        datetime(6) created_at "생성일"
        datetime(6) updated_at "수정일"
        datetime(6) deleted_at "삭제일"
    }

    SEAT {
        bigint seat_id PK "AUTO_INCREMENT, 좌석 ID"
        varchar(255) number "좌석 번호"
        bigint theater_id FK "극장 ID"
        datetime(6) created_at "생성일"
        datetime(6) updated_at "수정일"
        datetime(6) deleted_at "삭제일"
    }

    RESERVATION {
        bigint reservation_id PK "AUTO_INCREMENT, 예약 ID"
        bigint user_id FK "사용자 ID"
        bigint screening_id FK "상영 ID"
        bigint seat_id FK "좌석 ID"
        datetime(6) created_at "생성일"
        datetime(6) updated_at "수정일"
        datetime(6) deleted_at "삭제일"
    }

    USER {
        bigint user_id PK "AUTO_INCREMENT, 사용자 ID"
        varchar(255) username "사용자 이름"
        datetime(6) created_at "생성일"
        datetime(6) updated_at "수정일"
        datetime(6) deleted_at "삭제일"
    }

    MOVIE ||--o{ SCREENING: "has"
    THEATER ||--o{ SCREENING: "has"
    THEATER ||--|{ SEAT: "has"
    SCREENING ||--o{ RESERVATION: "has"
    SEAT ||-- || RESERVATION: "has"
    USER ||--o{ RESERVATION: "makes"
```
