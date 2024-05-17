-- 创建数据库
CREATE DATABASE Pre_Qin_Virtuous_Lady_Step;
USE Pre_Qin_Virtuous_Lady_Step;

-- 用户表
CREATE TABLE User
(
    UserID           INT PRIMARY KEY AUTO_INCREMENT,
    Name             VARCHAR(50)               NOT NULL,
    Age              INT,
    Gender           ENUM ('男', '女', '其他') NOT NULL,
    Email            VARCHAR(100) UNIQUE       NOT NULL,
    PhoneNumber      VARCHAR(20) UNIQUE        NOT NULL,
    School           VARCHAR(100),
    College          VARCHAR(100),
    Major            VARCHAR(100),
    Class            VARCHAR(50),
    RegistrationDate DATE                      NOT NULL,
    INDEX idx_email (Email),
    INDEX idx_phone (PhoneNumber)
) COMMENT ='用户表';

-- 跑步记录表
CREATE TABLE RunningRecord
(
    RecordID             INT PRIMARY KEY AUTO_INCREMENT,
    UserID               INT,
    RunningDate          DATE         NOT NULL,
    Distance             FLOAT        NOT NULL,
    Duration             TIME         NOT NULL,
    StartPosition        VARCHAR(255) NOT NULL, -- 调整长度以存储详细地址
    EndPosition          VARCHAR(255) NOT NULL, -- 调整长度以存储详细地址
    START_POSITION_POINT POINT,                 -- 添加地理坐标字段
    END_POSITION_POINT   POINT,                 -- 添加地理坐标字段
    FOREIGN KEY (UserID) REFERENCES User (UserID),
    INDEX idx_date (RunningDate),
    INDEX idx_user (UserID)
) COMMENT ='跑步记录表';