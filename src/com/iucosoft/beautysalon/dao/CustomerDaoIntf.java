package com.iucosoft.beautysalon.dao;

import com.iucosoft.beautysalon.models.Customer;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rusanovschi
 */
public interface CustomerDaoIntf {

    int save(Customer c) throws SQLException;

    void update(Customer c) throws SQLException;

    void delete(Customer c) throws SQLException;

    Customer findById(int id) throws SQLException;

    List<Customer> findByName(String nameC) throws SQLException;

    List<Customer> findAll() throws SQLException;

    List<Customer> findAll(String nameC, boolean startWith) throws SQLException;

}
