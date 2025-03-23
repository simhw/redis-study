import http from 'k6/http';
import {check} from 'k6';

const BASE_URL = 'http://localhost:8080';
const DAU = 150000;
const REQUESTS_PER_USER = 2;
const PEAK_MULTIPLIER = 10;

const RPD = DAU * REQUESTS_PER_USER;
const AVG_RPS = Math.trunc(RPD / 86400);
const PEAK_RPS = AVG_RPS * PEAK_MULTIPLIER;

export const options = {
    scenarios: {
        constant_load: {
            executor: 'constant-arrival-rate',  // 지정된 기간 동안 고정된 수의 반복
            rate: PEAK_RPS, // 초당 요청 수 (최대 RPS)
            timeUnit: '1s', // 초당 rate 반복
            duration: '5m', // 테스트 지속 시간
            preAllocatedVUs: 10, // 테스트 시작 전 vu
            maxVUs: 100, // 최대 회전 vu
        },
    },
    thresholds: {
        http_req_duration: ['p(95)<200'], // 95% 요청의 응답 시간이 200ms 이하
        http_req_failed: ['rate<0.01'], // 실패율이 1% 미만
    },
}

export function setup() {
}

export default function (data) {
    let res = http.get(`${BASE_URL}/api/v1/movies/now-showing?title=Tomb%2C%20The&genre=ACTION`);
    // let res = http.get(`${BASE_URL}/api/v1/movies/now-showing`);
    check(res, {
        'status is 200': (r) => r.status === 200,
    });
}

export function teardown(data) {
}




