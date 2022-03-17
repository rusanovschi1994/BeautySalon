package com.iucosoft.beautysalon.view.authentication;

import com.iucosoft.beautysalon.dao.MyDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Authentication {

    MyDataSource ds = MyDataSource.getInstance();
    Connection connection;

    public Authentication() {
        try {
            this.connection = ds.getConnection();
            if (connection == null) {
                System.out.println("Conexiunea la baza de date nu este posibila");
                System.exit(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isDbConnection() {
        try {
            return !connection.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("EROARE");
        }
        return false;
    }

    public boolean isLogin(String login, String password) throws SQLException {

        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
        try {

            pstat = connection.prepareStatement(sql);
            pstat.setString(1, login);
            pstat.setString(2, password);

            rs = pstat.executeQuery();

            if (rs.next()) {
                System.out.println("Loghinul si parola au fost introduse corect");
                return true;
            } else {
                showMessage(AlertType.ERROR, "Eroare",
                        "EROARE LA INTRODUCEREA DATELOR",
                        "Loginul sau parola introdusă este incorectă");
                System.out.println("Loghinul si parola au fost introduse incorect");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showMessage(Alert.AlertType type, String title, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }
}
