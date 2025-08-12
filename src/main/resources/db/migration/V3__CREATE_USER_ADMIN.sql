TRUNCATE authorities CASCADE;
TRUNCATE users CASCADE;
INSERT INTO users(username, password, enabled) VALUES ('admin', '$2a$10$A9bLZsJm5w7NlFkd0C76xuNIMI3cgQY8oIawWIiwJr0nRMkjEEoFK', true);
INSERT INTO authorities(username, authority) VALUES
     ('admin', 'ROLE_USER'),
     ('admin', 'ROLE_ADMIN');