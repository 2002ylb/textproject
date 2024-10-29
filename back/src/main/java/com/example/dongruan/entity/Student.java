package com.example.dongruan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data

public class Student {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer  id;
    private String name;
    private Integer  age;
    private String number;
    private String address;
//    @TableField("class_id")
//    private Integer classid;//可以写成classId  大写I 等于=i+下划线
    @TableField(exist = false)
//    @JsonIgnore  如果当你返回的结果集json不需要这个数据就可以用这个注解
    private Class myclassentity;
    @TableField(exist = false)
    private List<Course> courses;
}