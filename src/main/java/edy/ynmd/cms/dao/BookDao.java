package edy.ynmd.cms.dao;

import edy.ynmd.cms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book,String> {
}
