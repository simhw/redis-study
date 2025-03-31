-- 상영관
insert into theater(theater_id, created_at, name)
values (1, '2025-03-01 00:00:00', '1관');

-- 좌석
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (1, '2025-03-01 00:00:00', 1, 'A', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (2, '2025-03-01 00:00:00', 2, 'A', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (3, '2025-03-01 00:00:00', 3, 'A', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (4, '2025-03-01 00:00:00', 4, 'A', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (5, '2025-03-01 00:00:00', 5, 'A', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (6, '2025-03-01 00:00:00', 6, 'B', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (7, '2025-03-01 00:00:00', 7, 'B', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (8, '2025-03-01 00:00:00', 8, 'B', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (9, '2025-03-01 00:00:00', 9, 'B', 1);
insert into seat(seat_id, created_at, seat_no_number, seat_no_row, theater_id)
values (10, '2025-03-01 00:00:00', 10, 'B', 1);

-- 영화
insert into movie (movie_id, title, thumbnail, runtime, genre, rating, release_at, created_at, updated_at, deleted_at,
                   created_by, updated_by)
values (1, 'Dark Knight Rises, The', 'http://dummyimage.com/235x100.png/ff4444/ffffff', 380, 'ACTION', 'R12',
        '2025-03-12', '2025-02-12', null, null, null, null);

-- 상영
insert into screening (screening_id, movie_id, start_at, end_at, theater_id, created_at)
values (1, 1, date_add(now(), interval 1 hour), date_add(now(), interval 3 hour), 1, '2025-03-01 00:00:00');

-- 상영 좌석
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (1, false, 1, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (2, false, 2, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (3, false, 3, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (4, false, 4, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (5, false, 5, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (6, false, 6, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (7, false, 7, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (8, false, 8, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (9, false, 9, 1);
insert into allocated_seat(allocated_seat_id, reserved, seat_id, screening_id)
values (10, false, 10, 1);

-- 회원
insert into user (user_id, username)
values (1, 'user1');
insert into user (user_id, username)
values (2, 'user2');
insert into user (user_id, username)
values (3, 'user2');