CREATE SCHEMA testdb;

CREATE TABLE IF NOT EXISTS `test_user` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    create_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO `test_user` (id, username, nickname, email, create_time) VALUES
                         (1, 'username-01', 'nickname-01', 'username-01@email.com', '2023-08-01 01:00:00'),
                         (2, 'username-02', 'nickname-02', 'username-02@email.com', '2023-08-02 02:00:00'),
                         (3, 'username-03', 'nickname-03', 'username-03@email.com', '2023-08-03 03:00:00'),
                         (4, 'username-04', 'nickname-04', 'username-04@email.com', '2023-08-04 04:00:00');