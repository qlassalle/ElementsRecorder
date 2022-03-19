INSERT INTO elements_recorder_schema.resource(id, name, description, rating, url, user_id, created_at, updated_at)
VALUES ('98b8fdc6-acf2-4e46-9419-150e42d078bd', 'Polygon', 'Layer 1 blockchain', 5, 'https://www.polygon.com',
        '00000000-0000-0000-0000-000000000001', '2022-01-01 19:00:00.00000', '2022-01-01 19:00:00.00000');

INSERT INTO elements_recorder_schema.tag(id, name, user_id, created_at, updated_at)
VALUES ('4da58133-8565-4a90-b1a6-6fba50af6602', 'Crypto', '00000000-0000-0000-0000-000000000001',
        '2022-03-16 22:27:07.00000', '2022-03-16 22:27:07.00000'),
       ('31aafd17-8515-43f2-b6b5-87805a30dc20', 'Layer 1', '00000000-0000-0000-0000-000000000001',
        '2022-03-16 22:27:07.00000', '2022-03-16 22:27:07.00000'),
       ('f13400f1-b93d-40c9-ad5b-c3260550d44e', 'Eth killer', '00000000-0000-0000-0000-000000000001',
        '2022-03-16 22:27:07.00000', '2022-03-16 22:27:07.00000'),
       ('1a734472-d72d-4abd-bc43-21de17bbb9c0', 'Blockchain', '00000000-0000-0000-0000-000000000001',
        '2022-03-16 22:27:07.00000', '2022-03-16 22:27:07.00000'),
       ('1a45c23c-416e-4c8b-862c-605d164a2102', 'System Design', '00000000-0000-0000-0000-000000000001',
        '2022-03-16 22:27:07.00000', '2022-03-16 22:27:07.00000');

INSERT INTO elements_recorder_schema.tag_resource(tag_id, resource_id)
VALUES ('f13400f1-b93d-40c9-ad5b-c3260550d44e', '98b8fdc6-acf2-4e46-9419-150e42d078bd'),
       ('1a734472-d72d-4abd-bc43-21de17bbb9c0', '98b8fdc6-acf2-4e46-9419-150e42d078bd'),
       ('31aafd17-8515-43f2-b6b5-87805a30dc20', '98b8fdc6-acf2-4e46-9419-150e42d078bd'),
       ('4da58133-8565-4a90-b1a6-6fba50af6602', '98b8fdc6-acf2-4e46-9419-150e42d078bd'),
       -- tag belonging to another resource should never be picked up
       ('31aafd17-8515-43f2-b6b5-87805a30dc20', '00000000-0000-0000-0000-000000000002');