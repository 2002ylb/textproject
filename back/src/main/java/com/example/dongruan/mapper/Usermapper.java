package com.example.dongruan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dongruan.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Usermapper extends BaseMapper<User> {
    List<User> findaccount();
}
