-- 데이터베이스 생성
CREATE DATABASE payhere DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci
-- 사용자 생성
CREATE USER 'pos'@'%' IDENTIFIED BY 'password';
-- 사용자 권한 부여
grant all privileges on payhere.* to 'pos'@'%';
-- 데이터베이스 사용
use payhere;

-- users TABLE DDL SCRIPT
CREATE TABLE `users` (
                         `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `created_date` datetime DEFAULT NULL,
                         `modified_date` datetime DEFAULT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `role` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`user_id`)
                      ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ledger TABLE DDL SCRIPT
CREATE TABLE `ledger` (
                          `ledger_id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `created_date` datetime DEFAULT NULL,
                          `modified_date` datetime DEFAULT NULL,
                          `content` varchar(255) DEFAULT NULL,
                          `status` varchar(255) DEFAULT NULL,
                          `title` varchar(255) DEFAULT NULL,
                          `used_money` decimal(19,2) DEFAULT NULL,
                          `user_id` bigint(20) DEFAULT NULL,
                          PRIMARY KEY (`ledger_id`),
                          CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
                        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
