DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, calories, datetime) VALUES
  (100000, 'Завтрак', 500, '2018-12-31 9:00'::timestamp),
  (100000, 'Обед', 1000, '2018-12-31 14:00'::timestamp),
  (100000, 'Ужин', 500, '2018-12-31 21:00'::timestamp),

  (100001, 'Завтрак', 400, '2018-12-30 9:00'::timestamp),
  (100001, 'Обед', 1100, '2018-12-30 14:00'::timestamp),
  (100001, 'Ужин', 600, '2018-12-30 21:00'::timestamp);
