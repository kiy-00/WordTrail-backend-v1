-- ----------------------------
-- APP用户表
-- ----------------------------
drop table if exists app_user;
create table app_user (
                          user_id           bigint(20)      not null auto_increment    comment '用户ID',
                          username          varchar(30)     not null                   comment '用户账号',
                          password          varchar(100)    not null                   comment '密码',
                          phone_number      varchar(11)     default null               comment '手机号码',
                          email             varchar(50)     default null               comment '邮箱',
                          nickname          varchar(30)     default null               comment '用户昵称',
                          avatar            varchar(100)    default ''                 comment '头像地址',
                          status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
                          last_login_ip     varchar(128)    default ''                 comment '最后登录IP',
                          last_login_time   datetime                                   comment '最后登录时间',
                          create_time       datetime                                   comment '创建时间',
                          update_time       datetime                                   comment '更新时间',
                          remark            varchar(500)    default null               comment '备注',
                          create_by         varchar(64)     default ''                 comment '创建者',
                          update_by         varchar(64)     default ''                 comment '更新者',
                          del_flag          char(1)         default '0'                comment '删除标志（0代表存在 1代表删除）',
                          primary key (user_id),
                          unique key idx_username (username),
                          unique key idx_phone (phone_number),
                          unique key idx_email (email)
) engine=innodb auto_increment=100 comment = 'APP用户表';

-- ----------------------------
-- 初始化-APP用户表数据
-- ----------------------------
insert into app_user values(101, 'appuser01', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000001', 'appuser01@example.com', '测试用户01', '', '0', '192.168.1.1', DATE_SUB(sysdate(), INTERVAL 10 DAY), DATE_SUB(sysdate(), INTERVAL 10 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(102, 'appuser02', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000002', 'appuser02@example.com', '测试用户02', '', '0', '192.168.1.2', DATE_SUB(sysdate(), INTERVAL 9 DAY), DATE_SUB(sysdate(), INTERVAL 9 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(103, 'appuser03', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000003', 'appuser03@example.com', '测试用户03', '', '0', '192.168.1.3', DATE_SUB(sysdate(), INTERVAL 8 DAY), DATE_SUB(sysdate(), INTERVAL 8 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(104, 'appuser04', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000004', 'appuser04@example.com', '测试用户04', '', '0', '192.168.1.4', DATE_SUB(sysdate(), INTERVAL 7 DAY), DATE_SUB(sysdate(), INTERVAL 7 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(105, 'appuser05', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000005', 'appuser05@example.com', '测试用户05', '', '0', '192.168.1.5', DATE_SUB(sysdate(), INTERVAL 6 DAY), DATE_SUB(sysdate(), INTERVAL 6 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(106, 'appuser06', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000006', 'appuser06@example.com', '测试用户06', '', '0', '192.168.1.6', DATE_SUB(sysdate(), INTERVAL 5 DAY), DATE_SUB(sysdate(), INTERVAL 5 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(107, 'appuser07', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000007', 'appuser07@example.com', '测试用户07', '', '0', '192.168.1.7', DATE_SUB(sysdate(), INTERVAL 4 DAY), DATE_SUB(sysdate(), INTERVAL 4 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(108, 'appuser08', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000008', 'appuser08@example.com', '测试用户08', '', '0', '192.168.1.8', DATE_SUB(sysdate(), INTERVAL 3 DAY), DATE_SUB(sysdate(), INTERVAL 3 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(109, 'appuser09', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000009', 'appuser09@example.com', '测试用户09', '', '0', '192.168.1.9', DATE_SUB(sysdate(), INTERVAL 2 DAY), DATE_SUB(sysdate(), INTERVAL 2 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(110, 'appuser10', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000010', 'appuser10@example.com', '测试用户10', '', '0', '192.168.1.10', DATE_SUB(sysdate(), INTERVAL 1 DAY), DATE_SUB(sysdate(), INTERVAL 1 DAY), null, '普通用户', 'admin', '', '0');
insert into app_user values(111, 'appuser11', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000011', 'appuser11@example.com', '测试用户11', '', '0', '192.168.1.11', sysdate(), sysdate(), null, '普通用户', 'admin', '', '0');
insert into app_user values(112, 'appuser12', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '13900000012', 'appuser12@example.com', '测试用户12', '', '1', '192.168.1.12', sysdate(), sysdate(), null, '已停用用户', 'admin', '', '0');

-- ----------------------------
-- APP用户签到表
-- ----------------------------
drop table if exists app_user_checkin;
create table app_user_checkin (
                                  checkin_id        bigint(20)      not null auto_increment    comment '签到ID',
                                  user_id           bigint(20)      not null                   comment '用户ID',
                                  checkin_time      datetime        not null                   comment '签到时间',
                                  checkin_date      date            not null                   comment '签到日期',
                                  continuous_days   int(11)         default 1                  comment '连续签到天数',
                                  rewards           int(11)         default 0                  comment '签到奖励积分',
                                  ip_address        varchar(128)    default ''                 comment '签到IP地址',
                                  device_id         varchar(64)     default null               comment '设备标识',
                                  create_time       datetime                                   comment '创建时间',
                                  remark            varchar(500)    default null               comment '备注',
                                  primary key (checkin_id),
                                  unique key idx_user_date (user_id, checkin_date),
                                  key idx_checkin_date (checkin_date),
                                  key idx_user_id (user_id)
) engine=innodb auto_increment=1 comment = '用户签到记录表';

-- ----------------------------
-- 初始化-APP用户签到表数据 - 用户101连续签到10天的记录
-- ----------------------------
insert into app_user_checkin values(1, 101, DATE_SUB(sysdate(), INTERVAL 9 DAY), DATE_SUB(curdate(), INTERVAL 9 DAY), 1, 5, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 9 DAY), '首次签到');
insert into app_user_checkin values(2, 101, DATE_SUB(sysdate(), INTERVAL 8 DAY), DATE_SUB(curdate(), INTERVAL 8 DAY), 2, 5, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 8 DAY), '连续签到');
insert into app_user_checkin values(3, 101, DATE_SUB(sysdate(), INTERVAL 7 DAY), DATE_SUB(curdate(), INTERVAL 7 DAY), 3, 5, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 7 DAY), '连续签到');
insert into app_user_checkin values(4, 101, DATE_SUB(sysdate(), INTERVAL 6 DAY), DATE_SUB(curdate(), INTERVAL 6 DAY), 4, 5, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 6 DAY), '连续签到');
insert into app_user_checkin values(5, 101, DATE_SUB(sysdate(), INTERVAL 5 DAY), DATE_SUB(curdate(), INTERVAL 5 DAY), 5, 10, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 5 DAY), '连续5天签到奖励');
insert into app_user_checkin values(6, 101, DATE_SUB(sysdate(), INTERVAL 4 DAY), DATE_SUB(curdate(), INTERVAL 4 DAY), 6, 5, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 4 DAY), '连续签到');
insert into app_user_checkin values(7, 101, DATE_SUB(sysdate(), INTERVAL 3 DAY), DATE_SUB(curdate(), INTERVAL 3 DAY), 7, 5, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 3 DAY), '连续签到');
insert into app_user_checkin values(8, 101, DATE_SUB(sysdate(), INTERVAL 2 DAY), DATE_SUB(curdate(), INTERVAL 2 DAY), 8, 5, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 2 DAY), '连续签到');
insert into app_user_checkin values(9, 101, DATE_SUB(sysdate(), INTERVAL 1 DAY), DATE_SUB(curdate(), INTERVAL 1 DAY), 9, 5, '192.168.1.1', 'device-101-1', DATE_SUB(sysdate(), INTERVAL 1 DAY), '连续签到');
insert into app_user_checkin values(10, 101, sysdate(), curdate(), 10, 15, '192.168.1.1', 'device-101-1', sysdate(), '连续10天签到奖励');

-- ----------------------------
-- 其他用户的签到记录
-- ----------------------------
insert into app_user_checkin values(11, 102, DATE_SUB(sysdate(), INTERVAL 3 DAY), DATE_SUB(curdate(), INTERVAL 3 DAY), 1, 5, '192.168.1.2', 'device-102-1', DATE_SUB(sysdate(), INTERVAL 3 DAY), '首次签到');
insert into app_user_checkin values(12, 102, DATE_SUB(sysdate(), INTERVAL 2 DAY), DATE_SUB(curdate(), INTERVAL 2 DAY), 2, 5, '192.168.1.2', 'device-102-1', DATE_SUB(sysdate(), INTERVAL 2 DAY), '连续签到');
insert into app_user_checkin values(13, 102, sysdate(), curdate(), 3, 5, '192.168.1.2', 'device-102-1', sysdate(), '连续签到');

insert into app_user_checkin values(14, 103, DATE_SUB(sysdate(), INTERVAL 5 DAY), DATE_SUB(curdate(), INTERVAL 5 DAY), 1, 5, '192.168.1.3', 'device-103-1', DATE_SUB(sysdate(), INTERVAL 5 DAY), '首次签到');
insert into app_user_checkin values(15, 103, DATE_SUB(sysdate(), INTERVAL 4 DAY), DATE_SUB(curdate(), INTERVAL 4 DAY), 2, 5, '192.168.1.3', 'device-103-1', DATE_SUB(sysdate(), INTERVAL 4 DAY), '连续签到');
insert into app_user_checkin values(16, 103, DATE_SUB(sysdate(), INTERVAL 1 DAY), DATE_SUB(curdate(), INTERVAL 1 DAY), 1, 5, '192.168.1.3', 'device-103-1', DATE_SUB(sysdate(), INTERVAL 1 DAY), '断签后重新签到');
insert into app_user_checkin values(17, 103, sysdate(), curdate(), 2, 5, '192.168.1.3', 'device-103-1', sysdate(), '连续签到');

insert into app_user_checkin values(18, 104, sysdate(), curdate(), 1, 5, '192.168.1.4', 'device-104-1', sysdate(), '首次签到');
insert into app_user_checkin values(19, 105, sysdate(), curdate(), 1, 5, '192.168.1.5', 'device-105-1', sysdate(), '首次签到');
insert into app_user_checkin values(20, 106, sysdate(), curdate(), 1, 5, '192.168.1.6', 'device-106-1', sysdate(), '首次签到');
insert into app_user_checkin values(21, 107, sysdate(), curdate(), 1, 5, '192.168.1.7', 'device-107-1', sysdate(), '首次签到');
insert into app_user_checkin values(22, 108, sysdate(), curdate(), 1, 5, '192.168.1.8', 'device-108-1', sysdate(), '首次签到');

-- ----------------------------
-- APP用户签到统计表
-- ----------------------------
drop table if exists app_user_checkin_stats;
create table app_user_checkin_stats (
                                        stats_id             bigint(20)      not null auto_increment    comment '统计ID',
                                        user_id              bigint(20)      not null                   comment '用户ID',
                                        total_days           int(11)         default 0                  comment '总签到天数',
                                        continuous_days      int(11)         default 0                  comment '当前连续签到天数',
                                        max_continuous_days  int(11)         default 0                  comment '最大连续签到天数',
                                        last_checkin_date    date            default null               comment '最后签到日期',
                                        create_time          datetime                                   comment '创建时间',
                                        update_time          datetime                                   comment '更新时间',
                                        primary key (stats_id),
                                        unique key idx_user_id (user_id)
) engine=innodb auto_increment=1 comment = '用户签到统计表';

-- ----------------------------
-- 初始化-APP用户签到统计表数据
-- ----------------------------
insert into app_user_checkin_stats values(1, 101, 10, 10, 10, curdate(), DATE_SUB(sysdate(), INTERVAL 9 DAY), sysdate());
insert into app_user_checkin_stats values(2, 102, 3, 3, 3, curdate(), DATE_SUB(sysdate(), INTERVAL 3 DAY), sysdate());
insert into app_user_checkin_stats values(3, 103, 4, 2, 2, curdate(), DATE_SUB(sysdate(), INTERVAL 5 DAY), sysdate());
insert into app_user_checkin_stats values(4, 104, 1, 1, 1, curdate(), sysdate(), sysdate());
insert into app_user_checkin_stats values(5, 105, 1, 1, 1, curdate(), sysdate(), sysdate());
insert into app_user_checkin_stats values(6, 106, 1, 1, 1, curdate(), sysdate(), sysdate());
insert into app_user_checkin_stats values(7, 107, 1, 1, 1, curdate(), sysdate(), sysdate());
insert into app_user_checkin_stats values(8, 108, 1, 1, 1, curdate(), sysdate(), sysdate());
insert into app_user_checkin_stats values(9, 109, 0, 0, 0, null, sysdate(), sysdate());
insert into app_user_checkin_stats values(10, 110, 0, 0, 0, null, sysdate(), sysdate());
insert into app_user_checkin_stats values(11, 111, 0, 0, 0, null, sysdate(), sysdate());
insert into app_user_checkin_stats values(12, 112, 0, 0, 0, null, sysdate(), sysdate());