package com.iucosoft.beautysalon.dao;

import com.iucosoft.beautysalon.models.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rusanovschi
 */
public interface UserDaoIntf {

    void save(User u) throws SQLException;

    void update(User u) throws SQLException;

    void delete(User u) throws SQLException;

    User findById(int id) throws SQLException;
    
    User findByLogin(String login) throws SQLException;
    
    User findByLoghinAndPass(String loghin, String password) throws SQLException;
    
    List<User> findAll() throws SQLException;

    List<User> findByName(String nameU) throws SQLException;

    List<User> findAll(String nameU, boolean startWith) throws SQLException;

}
