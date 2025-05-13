package com.gm.gmserver.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: hhj023
 * @Date: 2025/5/13
 * @Description:
 */
@Data
@TableName("group_info")
public class GroupInfo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long ownerId;
}
