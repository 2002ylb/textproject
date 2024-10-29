package com.example.dongruan.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dongruan.entity.Student;
import com.example.dongruan.mapper.Studentmapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StudentserviceImpl extends ServiceImpl<Studentmapper, Student> implements Studentservice{
    private Gson gson = new Gson();

    public boolean add_update(Student student){

        return saveOrUpdate(student);
    }

   public String getall(){
       List<Student> studentlist = list();
        return gson.toJson(studentlist);
    }


}
