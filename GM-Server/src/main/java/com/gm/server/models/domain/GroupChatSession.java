package com.gm.server.models.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * @Author: hhj023
 * @Date: 2025/5/13
 * @Description:
 */
@Data
@TableName("group_chat_session")
public class GroupChatSession {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long seqId;

    private String content;

    private Long fromUserId;

    private Long toGroupId;

    private Integer status;

    private Date sendTime;
}
