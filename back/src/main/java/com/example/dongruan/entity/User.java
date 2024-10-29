package com.example.dongruan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    Integer id;
    String username;
    String password;
    @TableField(exist = false)
    Account account;
}
