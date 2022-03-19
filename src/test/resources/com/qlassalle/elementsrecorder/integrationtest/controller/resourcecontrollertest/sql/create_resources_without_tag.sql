INSERT INTO elements_recorder_schema.resource(id, name, description, rating, url, user_id, created_at, updated_at)
VALUES ('00000000-0000-0000-0000-000000000001', 'Java for noobs', 'Noobs should read this', 5, 'https://www.noobs.com',
        '00000000-0000-0000-0000-000000000001', '2021-06-01 19:00:00.00000', '2021-06-01 19:00:00.00000'),
       ('00000000-0000-0000-0000-000000000002', 'IT basics', 'Everything for integration testing', 2,
        'https://www.itesting.com', '00000000-0000-0000-0000-000000000001', '2021-06-01 19:00:00.00000',
        '2021-06-01 19:00:00.00000'),
       -- resource not belonging to any user
       ('00000000-0000-0000-0000-000000000003', 'IT basics', 'I do not exist',5,
        'https://www.itesting.com', '00000000-0000-0000-0000-000000000002', '2021-06-18 19:00:00.00000',
        '2021-06-18 19:00:00.00000');