DROP TABLE `test_user` IF EXISTS;
CREATE TABLE `test_user`
(
    id   bigint(5) primary key AUTO_INCREMENT,
    username varchar(30)
);

insert into test_user(username) values('schema');