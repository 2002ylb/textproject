package com.example.dongruan.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dongruan.entity.Student;
import com.example.dongruan.mapper.Studentmapper;
import com.example.dongruan.service.StudentserviceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/student")
public class Studentcontroller {
    @Autowired
    private StudentserviceImpl studentservice;
    @Autowired
    private Studentmapper studentmapper;
    private Gson gson = new Gson();

    /**
     * 得到全部信息
     * @return
     */
//    @GetMapping("/getall")
//    public String getdata(){
//        return  studentservice.getall();
//    }



    @GetMapping("/getall")
        public String getdata2() {
            List<Student> students= studentmapper.findALL();
            return gson.toJson(students);
        }
@PostMapping("/searchbyname")
public List<Student> searchByName(@RequestBody String name) {
    System.out.print(name);

    List<Student> studentList= studentmapper.selectbymyname(name);

    return  studentList;
}
    /**
     * 批量删除
     */
    @DeleteMapping(("/batchdelete"))
    public Result batchdelete(@RequestBody List<Integer> ids){

        boolean yesorno= studentservice.removeBatchByIds(ids);
      Integer code =yesorno?Code.DELETE_OK:Code.DELETE_ERR;
        String msg =yesorno?"成功":"失败";
        return new Result(code,msg);
    }

@PostMapping("/deletebyid")
    public int deletebyid(@RequestBody Student student){

        return studentmapper.deleteById(student);
}

/**
 * 更新
 */
@PostMapping("/updatestudent")
    public boolean updatestudent(@RequestBody Student student){
  return   studentservice.add_update(student);
}




/**
 * 分页(自己写的)
 */
//@GetMapping("/page")
//    public Map<String, Object> findpage(@RequestParam Integer pagenum, @RequestParam Integer pagesize){
//    Integer total=studentmapper.gettotal();
//    pagenum=(pagenum-1)*pagesize;
//    List<Student> data = studentmapper.findpage(pagenum, pagesize);
//    HashMap<String, Object> pagemap = new HashMap<>();
//    pagemap.put("data",data);
//    pagemap.put("total",total);
//    return pagemap;
//}

/**
 * 分页mybatis-plus
 */
@GetMapping("/page")
public IPage<Student> findpage(@RequestParam Integer pagenum, @RequestParam Integer pagesize){
    IPage<Student> page=new Page<>(pagenum,pagesize);
    QueryWrapper<Student> QueryWrapper = new QueryWrapper<>();
    QueryWrapper.orderByDesc("id");
    return studentservice.page(page,QueryWrapper);
}


/**
 * 新增
 */
@PostMapping("/insertstudent")
    public Boolean  insertstudent(@RequestBody Student student){
    return  studentservice.add_update(student);
}


/**
 * 连表查询(多对一 association   学生找班级)
 */
@GetMapping("/findstudentclass")
    public List<Student> findstudentclass(){
    return  studentmapper.findstudentclass();
}



    /**
     * 连表查询(一对多 collection 班级找学生)
     */
    @GetMapping  ("/findclassstudent")
    public List<Student> findclassstudent(@RequestParam Integer classid){
        return  studentmapper.selectstudentbyclass(classid);
    }

    /**
     * 连表查询(多对多 collection 学生与课程)
     */
    @GetMapping  ("/findstudentcourse")
    public List<Student> findstudentcourse(){
        return  studentmapper.selectstudentcourse();
    }
}