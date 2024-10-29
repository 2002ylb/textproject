package com.example.dongruan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dongruan.entity.Course;
import com.example.dongruan.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface Studentmapper extends BaseMapper<Student> {
    @Select("select * from student")
    List<Student> findALL();

    List<Student> selectbymyname(@Param("name") String name);

   Integer gettotal();

   List<Student> findpage(Integer pagenum,Integer pagesize);

   //多对一
   List<Student> findstudentclass();
//一对多
    List<Student> selectstudentbyclass(@Param("classid") Integer classid);

    //多对多
    List<Student> selectstudentcourse();
}
