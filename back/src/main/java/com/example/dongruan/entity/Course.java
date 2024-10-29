package com.example.dongruan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Course {
    @TableId(value = "id",type = IdType.AUTO)
    Integer id;
    String course;
    String courseTeacher;
}
