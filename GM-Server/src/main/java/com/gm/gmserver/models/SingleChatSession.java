package com.gm.gmserver.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

/**
 * @Author: hhj023
 * @Date: 2025/5/13
 * @Description:
 */
@Data
@TableName("single_chat_session")
public class SingleChatSession {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String cid;

    private Long seqId;

    private String content;

    private Long fromUserId;

    private Long toUserId;

    private Integer status;

    private Date sendTime;
}

