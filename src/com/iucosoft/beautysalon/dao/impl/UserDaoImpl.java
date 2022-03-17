package com.iucosoft.beautysalon.dao.impl;

import com.iucosoft.beautysalon.dao.MyDataSource;
import com.iucosoft.beautysalon.dao.UserDaoIntf;
import com.iucosoft.beautysalon.models.User;
import com.iucosoft.beautysalon.models.UserRole;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Rusanovschi
 */
public class UserDaoImpl implements UserDaoIntf {

    private static final Logger LOG = Logger.getLogger(CustomerDaoImpl.class.getName());

    private MyDataSource ds = MyDataSource.getInstance();

    @Override
    public void save(User u) throws SQLException {
        String sql = "INSERT INTO users VALUES(null, ?, ?, ?, ?)";

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstat = conn.prepareStatement(sql);) {

            pstat.setString(1, u.getName());
            pstat.setString(2, u.getLogin());
            pstat.setString(3, u.getPassword());
            pstat.setString(4, u.getUserRole().getRole());
            pstat.executeUpdate();
        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void update(User u) throws SQLException {
        String sql = "UPDATE users SET userName = ?, login = ?, password = ?, role = ? WHERE userid =" + u.getId();

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstat = conn.prepareStatement(sql);) {

            pstat.setString(1, u.getName());
            pstat.setString(2, u.getLogin());
            pstat.setString(3, u.getPassword());
            pstat.setString(4, u.getUserRole().getRole());
            pstat.executeUpdate();
        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void delete(User u) throws SQLException {
        String sql = "DELETE FROM users WHERE userid = " + u.getId();

        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstat = conn.prepareStatement(sql);) {

            pstat.executeUpdate();
        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE userid = " + id;
        User users = null;

        try (
                Connection conn = ds.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {

            if (rs.next()) {

                String name = rs.getString(2);
                String loginU = rs.getString(3);
                String password = rs.getString(4);

                UserRole ur = null;
                String usersRole = rs.getString(5);

                switch (usersRole) {
                    case "Administrator":
                        ur = UserRole.ADMINISTRATOR;
                        break;
                    case "Manager":
                        ur = UserRole.MANAGER;
                        break;
                    case "Operator":
                        ur = UserRole.OPERATOR;
                        break;
                }

                users = new User(id, name, loginU, password, ur);

            } else {
                throw new SQLException(" USERS is NULL ");
            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }

        return users;

    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try (
                Connection conn = ds.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String login = rs.getString(3);
                String password = rs.getString(4);

                UserRole ur = null;
                String usersRole = rs.getString(5);

                switch (usersRole) {
                    case "Administrator":
                        ur = UserRole.ADMINISTRATOR;
                        break;
                    case "Manager":
                        ur = UserRole.MANAGER;
                        break;
                    case "Operator":
                        ur = UserRole.OPERATOR;
                        break;
                }

                User users = new User(id, name, login, password, ur);
                list.add(users);
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }
        return list;
    }

    @Override
    public List<User> findAll(String nameU, boolean startWith) throws SQLException {

        StringBuilder sbsql = new StringBuilder("SELECT * FROM users");
        List<User> list = new ArrayList<>();

        if (startWith) {
            sbsql.append(" WHERE userName like '" + nameU + "%'");
        } else if (startWith == false) {
            sbsql.append(" WHERE userName='" + nameU + "'");
        }
        try (
                Connection conn = ds.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sbsql.toString());) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String login = rs.getString(3);
                String password = rs.getString(4);

                UserRole ur = null;
                String usersRole = rs.getString(5);

                switch (usersRole) {
                    case "Administrator":
                        ur = UserRole.ADMINISTRATOR;
                        break;
                    case "Manager":
                        ur = UserRole.MANAGER;
                        break;
                    case "Operator":
                        ur = UserRole.OPERATOR;
                        break;
                }

                User users = new User(id, name, login, password, ur);
                list.add(users);
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }
        return list;
    }

    @Override
    public List<User> findByName(String nameU) throws SQLException {

        StringBuilder sbsql = new StringBuilder("SELECT *  FROM users WHERE userName").
                append(" like '" + nameU + "%'");

        List<User> list = new ArrayList<>();

        try (
                Connection conn = ds.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sbsql.toString());) {
            while (rs.next()) {

                int id = rs.getInt(1);
                String name = rs.getString(2);
                String login = rs.getString(3);
                String password = rs.getString(4);

                UserRole ur = null;
                String usersRole = rs.getString(5);

                switch (usersRole) {
                    case "Administrator":
                        ur = UserRole.ADMINISTRATOR;
                        break;
                    case "Manager":
                        ur = UserRole.MANAGER;
                        break;
                    case "Operator":
                        ur = UserRole.OPERATOR;
                        break;
                }

                User users = new User(id, name, login, password, ur);
                list.add(users);
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }

        return list;
    }

    @Override
    public User findByLoghinAndPass(String loghin, String password) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("SELECT * FROM users").
                append(" WHERE login ='" + loghin + "'").
                append(" AND password = '" + password + "");

        User user = new User();

        try (
                Connection conn = ds.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sqlSB.toString());) {

            if (rs.next()) {

                int id = rs.getInt(1);
                String name = rs.getString(2);
                String loginU = rs.getString(3);
                String pass = rs.getString(4);

                UserRole ur = null;
                String usersRole = rs.getString(5);

                switch (usersRole) {
                    case "Administrator":
                        ur = UserRole.ADMINISTRATOR;
                        break;
                    case "Manager":
                        ur = UserRole.MANAGER;
                        break;
                    case "Operator":
                        ur = UserRole.OPERATOR;
                        break;
                }
                user = new User(id, name, loginU, pass, ur);
            } else {
                throw new SQLException(" USERS is NULL ");
            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }
        return user;
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("SELECT * FROM users").
                append(" WHERE login ='" + login + "'");

        User user = new User();

        try (
                Connection conn = ds.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sqlSB.toString());) {

            if (rs.next()) {

                int id = rs.getInt(1);
                String name = rs.getString(2);
                String loginU = rs.getString(3);
                String pass = rs.getString(4);

                UserRole ur = null;
                String usersRole = rs.getString(5);

                switch (usersRole) {
                    case "Administrator":
                        ur = UserRole.ADMINISTRATOR;
                        break;
                    case "Manager":
                        ur = UserRole.MANAGER;
                        break;
                    case "Operator":
                        ur = UserRole.OPERATOR;
                        break;
                }
                user = new User(id, name, loginU, pass, ur);
            } else {
                throw new SQLException(" USERS is NULL ");
            }
        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new SQLException(e.getMessage());
        }
        return user;
    }
}
