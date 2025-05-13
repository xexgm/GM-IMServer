CREATE DATABASE IF NOT EXISTS gm_im_db;

USE gm_im_db;

-- 单聊会话表
CREATE TABLE single_chat_session
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    cid          VARCHAR(50)   NOT NULL COMMENT '会话ID（格式：较小用户ID_较大用户ID）',
    seq_id       BIGINT        NOT NULL COMMENT '消息序列号',
    content      VARCHAR(1024)          COMMENT '消息内容',
    from_user_id BIGINT        NOT NULL COMMENT '发送者用户ID',
    to_user_id   BIGINT        NOT NULL COMMENT '接收者用户ID',
    status       INT                    COMMENT '消息状态 (已推送、未推送)',
    send_time    DATETIME      NOT NULL COMMENT '发送时间',
    INDEX        idx_cid (cid),
    INDEX        idx_send_time (send_time)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COMMENT = '单聊会话表';

-- 群聊会话表
CREATE TABLE group_chat_session
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    seq_id       BIGINT        NOT NULL COMMENT '消息序列号',
    content      VARCHAR(1024) NOT NULL COMMENT '消息内容',
    from_user_id BIGINT        NOT NULL COMMENT '发送者用户ID',
    to_group_id  BIGINT        NOT NULL COMMENT '接收群ID',
    status       INT                    COMMENT '消息状态（0-发送中 1-已送达 2-已读）',
    send_time    DATETIME      NOT NULL COMMENT '发送时间',
    INDEX        idx_group (to_group_id),
    INDEX        idx_send_time (send_time)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COMMENT = '群聊会话表';

-- 群聊关系表
CREATE TABLE group_chat_relation
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    group_id       BIGINT      NOT NULL COMMENT '群ID',
    user_id        BIGINT      NOT NULL COMMENT '用户ID',
    user_nick_name VARCHAR(50)          COMMENT '用户群昵称',
    quit           TINYINT     NOT NULL DEFAULT 0 COMMENT '是否退出（0-未退出 1-已退出）',
    quit_time      DATETIME             COMMENT '退出时间',
    INDEX          idx_group (group_id),
    INDEX          idx_user (user_id),
    UNIQUE uniq_group_user (group_id, user_id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COMMENT = '群聊关系表';

-- 群聊表
CREATE TABLE group_info
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增群ID',
    name     VARCHAR(50) NOT NULL COMMENT '群名称',
    owner_id BIGINT      NOT NULL COMMENT '群主ID',
    INDEX    idx_owner (owner_id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COMMENT = '群聊基础信息表';