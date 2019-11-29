package edy.ynmd.cms.dao;

import edy.ynmd.cms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,String>{
}
