INSERT INTO `users` (`id`, `phone_number`) VALUES
    (1L, '010-1111-2222'),
    (2L, '010-1111-2223'),
    (3L, '010-1111-2224'),
    (4L, '010-1111-2225'),
    (5L, '010-1111-2226'),
    (6L, '010-1111-2227'),
    (7L, '010-1111-2228'),
    (8L, '010-1111-2229'),
    (9L, '010-1111-2230'),
    (10L, '010-1111-2231'),
    (11L, '010-1111-2232'),
    (12L, '010-1111-2233'),
    (13L, '010-1111-2234'),
    (14L, '010-1111-2235'),
    (15L, '010-1111-2236'),
    (16L, '010-1111-2237'),
    (17L, '010-1111-2238'),
    (18L, '010-1111-2239'),
    (19L, '010-1111-2240'),
    (20L, '010-1111-2241'),
    (21L, '010-1111-2242'),
    (22L, '010-1111-2243'),
    (23L, '010-1111-2244'),
    (24L, '010-1111-2245'),
    (25L, '010-1111-2246'),
    (26L, '010-1111-2247'),
    (27L, '010-1111-2248'),
    (28L, '010-1111-2249'),
    (29L, '010-1111-2250'),
    (30L, '010-1111-2251'),
    (31L, '010-1111-2252'),
    (32L, '010-1111-2253'),
    (33L, '010-1111-2254'),
    (34L, '010-1111-2255'),
    (35L, '010-1111-2256'),
    (36L, '010-1111-2257'),
    (37L, '010-1111-2258'),
    (38L, '010-1111-2259'),
    (39L, '010-1111-2260'),
    (40L, '010-1111-2261'),
    (41L, '010-1111-2262'),
    (42L, '010-1111-2263'),
    (43L, '010-1111-2264'),
    (44L, '010-1111-2265'),
    (45L, '010-1111-2266'),
    (46L, '010-1111-2267'),
    (47L, '010-1111-2268'),
    (48L, '010-1111-2269'),
    (49L, '010-1111-2270'),
    (50L, '010-1111-2271'),
    (51L, '010-1111-2272'),
    (52L, '010-1111-2273'),
    (53L, '010-1111-2274'),
    (54L, '010-1111-2275'),
    (55L, '010-1111-2276'),
    (56L, '010-1111-2277'),
    (57L, '010-1111-2278'),
    (58L, '010-1111-2279'),
    (59L, '010-1111-2280'),
    (60L, '010-1111-2281'),
    (61L, '010-1111-2282'),
    (62L, '010-1111-2283'),
    (63L, '010-1111-2284'),
    (64L, '010-1111-2285'),
    (65L, '010-1111-2286'),
    (66L, '010-1111-2287'),
    (67L, '010-1111-2288'),
    (68L, '010-1111-2289'),
    (69L, '010-1111-2290'),
    (70L, '010-1111-2291'),
    (71L, '010-1111-2292'),
    (72L, '010-1111-2293'),
    (73L, '010-1111-2294'),
    (74L, '010-1111-2295'),
    (75L, '010-1111-2296'),
    (76L, '010-1111-2297'),
    (77L, '010-1111-2298'),
    (78L, '010-1111-2299'),
    (79L, '010-1111-2300'),
    (80L, '010-1111-2301'),
    (81L, '010-1111-2302'),
    (82L, '010-1111-2303'),
    (83L, '010-1111-2304'),
    (84L, '010-1111-2305'),
    (85L, '010-1111-2306'),
    (86L, '010-1111-2307'),
    (87L, '010-1111-2308'),
    (88L, '010-1111-2309'),
    (89L, '010-1111-2310'),
    (90L, '010-1111-2311'),
    (91L, '010-1111-2312'),
    (92L, '010-1111-2313'),
    (93L, '010-1111-2314'),
    (94L, '010-1111-2315'),
    (95L, '010-1111-2316'),
    (96L, '010-1111-2317'),
    (97L, '010-1111-2318'),
    (98L, '010-1111-2319'),
    (99L, '010-1111-2320'),
    (100L, '010-1111-2321');

insert into `user_point` (`id`, `user_id`, `point`, `version`)
values (1L, 1L, 0, 0);

insert into `concert` (`id`, `name`) values (1, '테스트 콘서트');

insert into `concert_detail` (`id`, `concert_id`, `concert_date`, `available_reservation_date`)
    values (1, 1, '2025-07-11 19:00:00', '2024-07-11 19:00:00');

insert into `concert_detail` (`id`, `concert_id`, `concert_date`, `available_reservation_date`)
    values (2, 1, '2025-07-10 19:00:00', '2024-07-11 19:00:00');

insert into `seat` (`id`, `concert_detail_id`, `seat_number`, `price`, `status`)
    values (1, 1, 1, 20000, 0);

insert into `seat` (`id`, `concert_detail_id`, `seat_number`, `price`, `status`)
    values (2, 1, 1, 20000, 1);

insert into `payment` (`id`, `reservation_id`, `payment_price`, `status`)
    values (1, 1, 20000, 0);

insert into `payment` (`id`, `reservation_id`, `payment_price`, `status`)
values (2, 1, 20000, 1);

insert into `user_token` (`id`, `user_id`, `status`)
 values (1, 1, 1);