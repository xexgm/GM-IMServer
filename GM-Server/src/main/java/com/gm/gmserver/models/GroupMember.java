package com.gm.gmserver.models;

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
@TableName("group_chat_relation")
public class GroupMember {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long groupId;

    private Long userId;

    private String userNickName;

    private Integer quit;

    private Date quitTime;
}
