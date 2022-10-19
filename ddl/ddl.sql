-- 데이터베이스 생성
CREATE DATABASE payhere DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci
-- 사용자 생성
CREATE USER 'pos'@'%' IDENTIFIED BY 'password';
-- 사용자 권한 부여
grant all privileges on payhere.* to 'pos'@'%';



