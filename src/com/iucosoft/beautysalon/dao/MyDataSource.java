package com.iucosoft.beautysalon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rusanovschi
 */
public class MyDataSource {

    private static final Logger LOG = Logger.getLogger(MyDataSource.class.getName());
    
    String driver = "com.mysql.jdbc.Driver";
    String dbURL = "jdbc:mysql://localhost:3306/salon";
    
    String username = "user";
    String password = "Micr0invest";
    
    Connection connection;
    
    public MyDataSource() {
        
        try {
            loadProprieties();
            loadDriver();
            loadConnect();
            
        } catch (ClassNotFoundException e) {
            LOG.log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public static MyDataSource getInstance() {
        return MyDataSourceHolder.INSTANCE;
    }

    private void loadProprieties() {
        LOG.info("Proprietatile au fost incarcate cu succes");
    }

    private void loadDriver() throws ClassNotFoundException {
        Class.forName(driver);
        System.out.println("Driverul " + driver + " a fost incarcat cu succes");

    }

    private void loadConnect() throws SQLException {
        connection = DriverManager.getConnection(dbURL, username, password);
        LOG.info("Conexiunea la baza de date a fost facuta cu succes");
        
    }

    public Connection getConnection() throws SQLException {
       if(connection == null || connection.isClosed() ){
           connection = DriverManager.getConnection(dbURL, username, password);
        }
        return connection;
    }
    
    private static class MyDataSourceHolder {

        private static final MyDataSource INSTANCE = new MyDataSource();
    }
}
