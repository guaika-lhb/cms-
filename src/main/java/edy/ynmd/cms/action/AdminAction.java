package edy.ynmd.cms.action;


import edy.ynmd.cms.model.*;
import edy.ynmd.cms.service.ManageService;
import edy.ynmd.cms.tools.OssTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("manage")
public class AdminAction {

    @Autowired
    private ManageService manageService;



    @PostMapping("addNews")
    @ResponseBody
    public boolean addNews(@RequestBody News news) throws Exception{
        news.setPbdata(System.currentTimeMillis());
        return manageService.addNews(news);

    }


    @PostMapping("deleteNews")
    @ResponseBody
    public boolean deleteNews(@RequestBody News news) throws Exception{
        return manageService.deleteNews(news.getNewsid());
    }

    @PostMapping("updateNews")
    @ResponseBody
    public boolean updateNews(@RequestBody News news) throws Exception{
        return manageService.updateNews(news);
    }


    @PostMapping("getSingleNews")
    @ResponseBody
    public News getSingleNews(@RequestBody News news) throws Exception{

        return manageService.getNews(news.getNewsid());
    }

    @GetMapping("getNewsList")
    @ResponseBody
    public List<News> getNewsList() throws Exception{

        return manageService.getNewsList();
    }



    @PostMapping("uploadNewsCoverpic")
    @ResponseBody
    public HashMap uploadNewsCoverpic(MultipartFile file, HttpServletRequest request) throws Exception{

        HashMap m=new HashMap();

        String filename= file.getOriginalFilename();

        String newsid =request.getHeader("newsid");//将newsid放在http header

        int index=filename.lastIndexOf(".");
        String suffexname=filename.substring(index);
        String tosavefilename=String.valueOf(System.currentTimeMillis())+suffexname;


        //检测上传文件目录是否存在
        String savepath="F:\\springbootupload\\";
//        String savepath="/root/project/picupload/";
        File f=new File(savepath);
        if(!f.exists()){
            f.mkdir();
        }
        //将上传的文件保存到该文件加下
        file.transferTo(new File(savepath+tosavefilename));

        //保存图片地址到数据库中
        News toupdatenews=manageService.getNews(newsid);
        if(toupdatenews!=null){
            toupdatenews.setCoverpic(tosavefilename);
            manageService.updateNews(toupdatenews);

        }

        m.put("filename",tosavefilename);
        m.put("newsid",newsid);

        return m;
    }




    @PostMapping("fileUpload")
    @ResponseBody
    public Map fileUpload(MultipartFile file) throws Exception{
        Map m=new HashMap();

        String filename=file.getOriginalFilename();
        System.out.printf("接收到的文件名是 "+filename);

        //获得文件的猴嘴
        int index=filename.lastIndexOf(".");
        String suffexname=filename.substring(index);
        String tosavefilename=String.valueOf(System.currentTimeMillis())+suffexname;

        //检测上传文件目录是否存在
//        String savepath="/root/project/picupload/";
       String savepath="F:\\springbootupload\\";
        File f=new File(savepath);
        if(!f.exists()){
            f.mkdir();
        }

        //将上传的文件保存到该文件加下
        file.transferTo(new File(savepath+tosavefilename));
        m.put("pic",tosavefilename);

        return m;
    }

    @RequestMapping("/ckeditorUpload")
    @ResponseBody

    public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws Exception {

        if (!file.isEmpty()) {
            String finename=file.getOriginalFilename();
            String suffixname=file.getOriginalFilename().substring(finename.lastIndexOf("."));

            finename=String.valueOf(System.currentTimeMillis())+suffixname;

//            String filepath="/root/project/picupload/";
              String filepath="F:/springbootupload/";

            File tf=new File(filepath);
            if(!tf.exists()){
                tf.mkdir();
            }
            String savefile=filepath+finename;
            try {
                File touploadfile=new File(savefile);
                file.transferTo(touploadfile);
                OssTools ossTools =new OssTools();
                ossTools.init();
                String osspath="/news/"+finename;

                String result= ossTools.uploadFireForUrl(osspath,touploadfile);

                System.out.println("上传文件的结果"+result);

//              String url="http://www.wbdqc.cn:81/"+finename;
                String url="http://localhost:81/public/"+finename;

//                String osshost="https://cms-1259095689.cos.ap-chengdu.myqcloud.com/news/";
//                String url=osshost+finename;

                return "{\"uploaded\":1,\"fileName\":\""+savefile+"\",\"url\":\"" + url + "\"}";

            } catch (IOException e) {
                e.printStackTrace();
                return "{\"uploaded\":0,\"error\":{\"message\":\"upload file is not success!\"}}";

            } catch (IllegalStateException e) {
                e.printStackTrace();
                return "{\"uploaded\":0,\"error\":{\"message\":\"upload file is not success!\"}}";

            }



        }


        return "{\"uploaded\":0,\"error\":{\"message\":\"upload file is not success!\"}}";
    }



    @PostMapping("addCarousel")
    @ResponseBody
    public boolean addCarousel(@RequestBody Carousel carousel) throws Exception{
        return manageService.addCarousel(carousel);
    }

    @PostMapping("updateCarousel")
    @ResponseBody
    public boolean updateCarousel(@RequestBody Carousel carousel) throws Exception{
        return manageService.updateCarousel(carousel);
    }

    @PostMapping("getCarousel")
    @ResponseBody
    public Carousel getCarousel(@RequestBody Carousel carousel) throws Exception{
        return manageService.getCarousel(carousel.getCarouselid());
    }



    @PostMapping("deleteCarousel")
    @ResponseBody
    public boolean deleteCarousel(@RequestBody Carousel carousel) throws Exception{
        return manageService.deleteCarousel(carousel.getCarouselid());

    }

    @GetMapping("getCarouselList")
    @ResponseBody
    public  List<Carousel> getCarouselList() throws Exception{


        return manageService.getCarouselList();
    }

    @PostMapping("addSinglepage")
    @ResponseBody
    public boolean addSinglepage(@RequestBody Singlepage singlepage) throws Exception{
        return manageService.addSinglepage(singlepage);
    }

    @PostMapping("updateSinglepage")
    @ResponseBody
    public boolean updateSinglepage(@RequestBody Singlepage singlepage) throws Exception{
        return manageService.updateSinglepage(singlepage);
    }

    @PostMapping("getSinglepage")
    @ResponseBody
    public Singlepage getSinglepage(@RequestBody Singlepage singlepage) throws Exception{
        return manageService.getSinglepage(singlepage.getSinglepageid());
    }

    @PostMapping("deleteSinglepage")
    @ResponseBody
    public boolean deleteSinglepage(@RequestBody Singlepage singlepage) throws Exception{
        return manageService.deleteSinglepage(singlepage.getSinglepageid());

    }

    @GetMapping("getSinglepageList")
    @ResponseBody
    public  List<Singlepage> getSinglepageList() throws Exception{


        return manageService.getSinglepageList();
    }


    @PostMapping("addBook")
    @ResponseBody
    public  boolean addBook(@RequestBody Book book) throws Exception{

        return manageService.addBook(book);
    }


    @PostMapping("deleteBook")
    @ResponseBody
    public  boolean deleteBook(@RequestBody Book book) throws Exception{
        return manageService.deleteBook(book.getId());
    }


    @PostMapping("updateBook")
    @ResponseBody
    public boolean updateBook(@RequestBody Book book) throws Exception{
        return  manageService.updateBook(book);
    }

    @PostMapping("getOneBook")
    @ResponseBody
    public Book getOneBook(@RequestBody Book book) throws Exception{
        return manageService.getBook(book.getId());
    }



    @PostMapping("addStudent")
    @ResponseBody
    public  boolean addStudent(@RequestBody Student student) throws Exception{

        return manageService.addStudent(student);
    }


    @PostMapping("deleteStudent")
    @ResponseBody
    public  boolean deleteStudent(@RequestBody Student student) throws Exception{
        return manageService.deleteStudent(student.getId());
    }


    @PostMapping("updateStudent")
    @ResponseBody
    public boolean updateStudent(@RequestBody Student student) throws Exception{
        return  manageService.updateStudent(student);
    }

    @PostMapping("getOneStudent")
    @ResponseBody
    public Student getOneStudent(@RequestBody Student student) throws Exception{
        return manageService.getStudent(student.getId());
    }


    @PostMapping("addClassroom")
    @ResponseBody
    public  boolean addClassroom(@RequestBody Classroom classroom) throws Exception{

        return manageService.addClassroom(classroom);
    }


    @PostMapping("deleteClassroom")
    @ResponseBody
    public  boolean deleteClassroom(@RequestBody Classroom classroom) throws Exception{
        return manageService.deleteClassroom(classroom.getId());
    }


    @PostMapping("updateClassroom")
    @ResponseBody
    public boolean updateClassroom(@RequestBody Classroom classroom) throws Exception{
        return  manageService.updateClassroom(classroom);
    }

    @PostMapping("getOneClassroom")
    @ResponseBody
    public Classroom getOneClassroom(@RequestBody Classroom classroom) throws Exception {
        return manageService.getClassroom(classroom.getId());
    }



    @PostMapping("addTeacher")
    @ResponseBody
    public  boolean addTeacher(@RequestBody Teacher teacher) throws Exception{

        return manageService.addTeacher(teacher);
    }


    @PostMapping("deleteTeacher")
    @ResponseBody
    public  boolean deleteTeacher(@RequestBody Teacher teacher) throws Exception{
        return manageService.deleteTeacher(teacher.getId());
    }


    @PostMapping("updateTeacher")
    @ResponseBody
    public boolean updateTeacher(@RequestBody Teacher teacher) throws Exception{
        return  manageService.updateTeacher(teacher);
    }


    @PostMapping("getOneTeacher")
    @ResponseBody
    public Teacher getOneTeacher(@RequestBody Teacher teacher) throws Exception{
        return manageService.getTeacher(teacher.getId());
    }



    @PostMapping("addCourse")
    @ResponseBody
    public boolean addCourse(@RequestBody Course course) throws Exception{

        return  manageService.addCourse(course);

    }


}