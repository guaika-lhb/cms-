package edy.ynmd.cms.dao;

import edy.ynmd.cms.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersDao extends JpaRepository<Users,String> {

    @Query("select u from Users u where u.username=:username and u.password=:password")
    Users getUsersByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}