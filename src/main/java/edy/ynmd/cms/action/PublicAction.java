package edy.ynmd.cms.action;

import edy.ynmd.cms.model.*;
import edy.ynmd.cms.service.ManageService;
import edy.ynmd.cms.tools.JwtUtil;
import edy.ynmd.cms.vo.Parms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

@Controller
@CrossOrigin
@RequestMapping("public")
public class PublicAction {


    @Autowired
    private ManageService manageService;


    @PostMapping("login")
    @ResponseBody
    public HashMap login(@RequestBody Users users)throws Exception{
        HashMap m=new HashMap();
        Users u=manageService.findUserByUsernameAndPassword(users.getUsername(),users.getPassword());

        if(u!=null){

            String token= JwtUtil.generateToken(u.getRole(),u.getUserid());


            m.put("msg","ok");
            m.put("token",token);

        }
        else {
            m.put("msg","error");
        }

        return m;

    }

    @GetMapping("getNewsList")
    @ResponseBody
    public List<News> getNewsList() throws Exception{

        return manageService.getNewsList();
    }


    @GetMapping("getCarouselList")
    @ResponseBody
    public  List<Carousel> getCarouselList() throws Exception{
        return manageService.getCarouselList();
    }


    @GetMapping("getBookList")
    @ResponseBody
    public List<Book> getBookList() throws Exception{
        return  manageService.getBookList();
    }

    @GetMapping("getStudentList")
    @ResponseBody
    public List<Student> getStudentList() throws Exception{
        return  manageService.getStudentList();
    }

    @GetMapping("getClassroomList")
    @ResponseBody
    public List<Classroom> getClassroomList() throws Exception{
        return  manageService.getClassroomList();
    }

    @GetMapping("getTeacherList")
    @ResponseBody
    public List<Teacher> getgetTeacherListList() throws Exception{
        return manageService.getTeacherList();

    }

    @PostMapping("getCourseListByParm")
    @ResponseBody
    public List<Course> getCourseListByParm(@RequestBody Parms parms) throws Exception{

        List<Course> cl =new ArrayList();

        switch (parms.getKey()){
            case "book":
                cl = manageService.getCourseByBook(parms.getValue());
                break;


            case "student":
                cl = manageService.getCourseByStudent(parms.getValue());
                break;

            case "classroom":
                cl = manageService.getCourseByClassroom(parms.getValue());
                break;

            case "teacher":
                cl = manageService.getCourseByTeacher(parms.getValue());
                break;



        }

        return  cl;


    }



}