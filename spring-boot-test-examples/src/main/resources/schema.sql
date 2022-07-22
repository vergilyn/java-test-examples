-- h2 --
-- DROP TABLE `test_user` IF EXISTS;

-- mysql --
DROP TABLE IF EXISTS `test_user` ;
CREATE TABLE `test_user`
(
    id          bigint(5) PRIMARY KEY AUTO_INCREMENT,
    username    varchar(30),
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

insert into test_user(username) values('spring-datasource-init');