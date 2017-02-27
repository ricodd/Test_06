package rico.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import rico.demo.domain.Pager;
import rico.demo.domain.Student;
import rico.demo.service.StudentService;

import java.io.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Rico.Chen on 2017/2/18.
 */

@Controller
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping
    public String toIndexPage(ModelMap modelMap, String limit, String offset) {



//        List<Student> studentList = this.studentService.getStudentList();
//        if (studentList != null) {
//            modelMap.put("studentList", studentList);
//        }
//        return "student/index";

        Pager pager = this.studentService.getStudentList(limit);
        log.info("pageNO = {}" + pager.getPagerNO());
        log.info("{}" + pager.getPagerSize());
        log.info("{}" + pager.getPagerCount());
        log.info("{}" + pager.getList().size());
//        if (pager.getList() != null) {
//            modelMap.put("studentList", pager.getList());
//        }

        modelMap.put("pager", pager);
        modelMap.put("today", Calendar.getInstance());
        return "student/index";
    }

    @RequestMapping("/modify/{id}")
    public String modifyStudent(@PathVariable Long id, ModelMap modelMap, String name, Integer age) {

        if(name == null && age == null) {
            log.info(String.valueOf(id));
            Student student = this.studentService.getStudent(id);
            if (student != null) {
                log.info(String.valueOf(student.getId()));
                log.info(student.getName());
                log.info(String.valueOf(student.getAge()));

                modelMap.put("student", student);
            }
            return "student/modify";
        } else {
            Student student = new Student(id, name, age);
            this.studentService.updateStudent(student);
            return "redirect:/student";
        }


    }

    @RequestMapping(value = "/new")
    public String newStudent(String name, Integer age) {
        if(name == null && age == null) {
            return "student/new";
        }else {

            log.info(name);
            log.info(age.toString());
            Student student = new Student(name, age);

            this.studentService.createStudent(student);
            return "redirect:/student";
        }
    }

    @RequestMapping("/remove/{id}")
    @ResponseBody
    public String removeStudent(@PathVariable  Long id) {

        log.info(String.valueOf(id));
        this.studentService.deleteStudent(id);
        return "success";
    }

    /**
     * 千万不要在ajax请求url后缀写成.html，否则始终报错：
     * @param id
     * @return
     */
    @RequestMapping(value = "/ajaxStudentById")
    @ResponseBody
    public Student ajaxStudentById(Long id) {
        log.info("{}" + id);
        Student student = this.studentService.getStudent(id);
//        log.info(student.getId() + student.getName() + student.getAge());
        log.info(student.toString());
        return student;
    }

    @RequestMapping(value = "/ajaxStudentList")
    @ResponseBody
    public List<Student> ajaxStudentList() {

       List<Student> studentList = this.studentService.getStudentList();
        if(studentList != null) {
            for(Student student : studentList) {
                log.info(student.toString());
            }
        }

        return studentList;
    }


//    @RequestMapping(value = "/ajaxStudentById.html")
//    public ResponseEntity<Student> ajaxStudentById(Long id) {
//        log.info("{}" + id);
//        Student student = this.studentService.getStudent(id);
////        log.info(student.getId() + student.getName() + student.getAge());
//        log.info(student.toString());
//
//        return new ResponseEntity<Student>(student, HttpStatus.OK);
//    }


//    @RequestMapping("/showImage.html")
//    public void showImage(HttpServletResponse response) {
////        log.info("{}",id);
//
//        try (InputStream inputStream =
//                new FileInputStream("d:\\example.jpg");
//             ServletOutputStream out = response.getOutputStream()){
//            byte[] b = new byte[1024];
//            int length = 0;
//            while((length = inputStream.read(b)) != -1) {
//                out.write(b, 0 ,length);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @RequestMapping(value = "/showImage")
    @ResponseBody
    public byte[] showImage() throws IOException{
        InputStream inputStream = new BufferedInputStream(
                new FileInputStream(
                        new File("d:\\example.jpg")));
        return IOUtils.toByteArray(inputStream);
    }

//    @RequestMapping(value = "/showImage.html")
//    public void showImage(HttpServletResponse response) throws IOException{
//        InputStream inputStream = new BufferedInputStream(
//                new FileInputStream(
//                        new File("d:\\example.jpg")));
//        IOUtils.copy(inputStream, response.getOutputStream());
//    }







}
