package com.example.dongruan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Class {
    @TableId(value = "id", type = IdType.AUTO)
        Integer id;
        String className;
        String classPeople;
        @TableField(exist = false)
        private List<Student> students;
}
