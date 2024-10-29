package com.example.dongruan.control;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dongruan.entity.Student;
import com.example.dongruan.mapper.Studentmapper;
import com.example.dongruan.service.Studentservice;
//import com.example.project_student.entity.StuFile;
//import com.example.project_student.entity.Student;
//import com.example.project_student.mapper.FileMapper;
//import com.example.project_student.mapper.StudentMapper;
//import com.example.project_student.service.Fileservicelmpl;
//import com.example.project_student.service.Studentservice;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


@RestController
//为了跨域
/**
 * 文件上传
 */
public class FileController {
    @Autowired
    private Studentmapper studentMapper;
    @Autowired
    private Studentservice studentservice;

//    @Autowired
//    private Fileservicelmpl fileservicelmpl;
//    @Autowired
//    private FileMapper fileMapper;
//    @Value("${files.upload.path}")
//    private String fileUploadPath;
//    @GetMapping("/{newFilename}")
//    //下载文件
//
//    public void download(@PathVariable String newFilename, HttpServletResponse response) throws IOException {
//        System.out.println(newFilename);
//        // 根据文件的唯一标识码获取文件
//        File uploadFile = new File( fileUploadPath +"//"+ newFilename);
//        System.out.println(uploadFile);
//        // 设置输出流的格式
//        ServletOutputStream os = response.getOutputStream();
//        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(newFilename,  "UTF-8"));
//        response.setContentType("application/octet-stream");
//
//        // 读取文件的字节流
//        os.write(FileUtil.readBytes(uploadFile));
//        os.flush();
//        os.close();
//
//    }
//
//    //用原始的sql语句进行全部删除
//    @GetMapping("/deleall")
//    public String deleall(){
//        fileMapper.deleteAll();
//        return "删除全部";
//    }
//
//    /**
//     * 文件下载
//     * @param file
//     * @return
//     * @throws IOException
//     */
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        String originalFilename = file.getOriginalFilename();
//
//        int size = (int) file.getSize();
//        String type = FileUtil.extName(originalFilename);
//
//        File uploadParentFile = new File(fileUploadPath);
//        if (!uploadParentFile.exists()) {
//            uploadParentFile.mkdirs(); // 使用mkdirs()来确保所有必需的父目录都被创建
//        }
//
//        String uuid = IdUtil.fastSimpleUUID();
//        String newFilename = uuid + StrUtil.DOT + type; // 创建一个新的文件名，仅包含UUID和扩展名
//        File uploadFile = new File(uploadParentFile, newFilename); // 指定父目录和文件名
//
//        //md5 的作用是当这个文件上传过一模一样的就不 file.transferTo(uploadFile)存入磁盘了
//
//
//
//// 计算 MD5 值
//        String md5 = SecureUtil.md5(originalFilename);
//
//// 创建查询条件
//        QueryWrapper<StuFile> fileQueryWrapper = new QueryWrapper<>();
//        fileQueryWrapper.eq("md5", md5);
//
//// 使用 selectList() 方法获取所有匹配的记录
//        List<StuFile> stuFileList = fileMapper.selectList(fileQueryWrapper);
//
//        String url;
//        if (stuFileList != null && !stuFileList.isEmpty()) {
//            // 找到了至少一条记录，不需要再存储文件，直接使用已存在的文件 URL
//            StuFile stuFile = stuFileList.get(0); // 获取第一条记录
//            url = stuFile.getUrl();
//            System.out.println("文件已存在，不需要重复上传。");
//        } else {
//            // 没有找到记录，需要存储文件
//            file.transferTo(uploadFile); // 将文件传输到指定位置
//            url = "http://localhost:9000/" + newFilename;
//            // 在这里，你可能还需要将新的文件记录插入到数据库中
//        }
//
//// 返回 URL 或进行其他操作...
//
//
//        System.out.println(uploadFile.getAbsolutePath());
//        StuFile SavaFile = new StuFile();
//        SavaFile.setSize(size);
//        SavaFile.setName(originalFilename);
//        SavaFile.setType(type);
//        SavaFile.setUrl(url);
//        SavaFile.setMd5(md5);
//
//        fileservicelmpl.save(SavaFile);
//        return url;
//    }

    /**
     * excel导出
     */
@GetMapping("/export")
    public void exportfile(HttpServletResponse response) throws IOException {
    List<Student> list = studentservice.list();
    ExcelWriter writer= ExcelUtil.getWriter(true);
//    writer.addHeaderAlias("name","用户名");
//    writer.addHeaderAlias("age","年龄");
//    writer.addHeaderAlias("number","电话号");
//    writer.addHeaderAlias("address","地址");
writer.write(list,true);
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
    String fileName = URLEncoder.encode("用户信息",  "UTF-8");
    response.setHeader("Content-Disposition",  "attachment;filename=" + fileName + ".xlsx");
    ServletOutputStream out=response.getOutputStream();
    writer.flush(out,true);
    out.close();
    writer.close();
}

/**
 * excel导入
 */
@PostMapping("/import")
    public boolean importexcel(@RequestBody MultipartFile file) throws IOException {
    InputStream inputStream = file.getInputStream();
    ExcelReader reader = ExcelUtil.getReader(inputStream);
    List<Student> list = reader.readAll(Student.class);
    studentservice.saveBatch(list);
return  true;
}
}
