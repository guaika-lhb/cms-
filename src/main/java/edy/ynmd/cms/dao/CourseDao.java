package edy.ynmd.cms.dao;

import edy.ynmd.cms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseDao extends JpaRepository<Course,String>{
    @Query("select c from Course c where c.book=:bookid")
    public List<Course>getCourseListByBook(@Param("bookid")String book);

    @Query("select c from Course c where c.student=:studentid")
    public List<Course>getCourseListByStudent(@Param("studentid")String student);

    @Query("select c from Course c where c.classroom=:classrooid")
    public List<Course>getCourseListByClassroom(@Param("classrooid")String classroom);

    @Query("select c from Course c where c.teacher=:teacherid")
    public List<Course>getCourseListByTeacher(@Param("teacherid")String teacher);
}
