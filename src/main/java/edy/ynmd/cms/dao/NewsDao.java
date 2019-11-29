package edy.ynmd.cms.dao;

import edy.ynmd.cms.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsDao extends JpaRepository<News,String> {
}

