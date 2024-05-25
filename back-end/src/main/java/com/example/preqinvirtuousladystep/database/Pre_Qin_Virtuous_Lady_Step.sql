-- 创建数据库
CREATE DATABASE Pre_Qin_Virtuous_Lady_Step;
USE Pre_Qin_Virtuous_Lady_Step;

-- 创建用户表
CREATE TABLE User
(
    UserID           INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户的唯一标识符，自动递增',
    Name             VARCHAR(50)               NOT NULL COMMENT '用户的姓名，不能为空',
    Password         VARCHAR(32)               NOT NULL COMMENT '用户的密码，不能为空，长度为32个字符',
    Age              INT COMMENT '用户的年龄',
    Gender           ENUM ('男', '女', '其他') COMMENT '用户的性别，只能是男、女或其他，不能为空',
    Email            VARCHAR(100) UNIQUE COMMENT '用户的电子邮件地址，唯一，至少Email或PhoneNumber之一不为空',
    PhoneNumber      VARCHAR(20) UNIQUE COMMENT '用户的电话号码，唯一，至少Email或PhoneNumber之一不为空',
    RegistrationDate DATE                      NOT NULL COMMENT '用户的注册日期，不能为空',
    CONSTRAINT chk_email_phone CHECK (Email IS NOT NULL OR PhoneNumber IS NOT NULL),
    INDEX idx_email (Email) COMMENT '对电子邮件地址列创建的索引，以便快速查询',
    INDEX idx_phone (PhoneNumber) COMMENT '对电话号码列创建的索引，以便快速查询'
) COMMENT ='用户表';


-- 创建跑步记录表
CREATE TABLE RunningRecord
(
    RecordID             INT PRIMARY KEY AUTO_INCREMENT COMMENT '跑步记录的唯一标识符，自动递增',
    UserID               INT COMMENT '关联到用户表的UserID，表示这条记录是由哪个用户创建的',
    RunningDate          DATE         NOT NULL COMMENT '跑步的日期，不能为空',
    Distance             FLOAT        NOT NULL COMMENT '跑步的距离，以公里为单位，不能为空',
    Duration             TIME         NOT NULL COMMENT '跑步的持续时间，不能为空',
    StartPosition        VARCHAR(255) NOT NULL COMMENT '跑步的起始位置，可以存储详细地址，不能为空',
    EndPosition          VARCHAR(255) NOT NULL COMMENT '跑步的结束位置，可以存储详细地址，不能为空',
    START_POSITION_POINT POINT COMMENT '跑步的起始位置的地理坐标（经纬度），可选',
    END_POSITION_POINT   POINT COMMENT '跑步的结束位置的地理坐标（经纬度），可选',
    FOREIGN KEY (UserID) REFERENCES User (UserID),
    INDEX idx_date (RunningDate) COMMENT '对跑步日期列创建的索引，以便快速查询',
    INDEX idx_user (UserID) COMMENT '对UserID列创建的索引，以便快速查询'
) COMMENT ='跑步记录表';
