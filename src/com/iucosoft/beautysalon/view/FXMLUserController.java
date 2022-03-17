package com.iucosoft.beautysalon.view;

import com.iucosoft.beautysalon.dao.UserDaoIntf;
import com.iucosoft.beautysalon.dao.impl.UserDaoImpl;
import com.iucosoft.beautysalon.fileservice.UserFileServiceIntf;
import com.iucosoft.beautysalon.fileservice.fileserviceDao.UserFileServiceImpl;
import com.iucosoft.beautysalon.models.User;
import com.iucosoft.beautysalon.models.UserRole;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * @author Rusanovschi
 */
public class FXMLUserController implements Initializable, FormularIntf<User> {

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfLoghin;
    @FXML
    private TextField tfUserId;
    @FXML
    private TextField tfPassword;
    @FXML
    private ComboBox<UserRole> tfusersRoles;
    @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, Integer> tableId;
    @FXML
    private TableColumn<User, String> tableName;
    @FXML
    private TableColumn<User, String> tableLogin;
    @FXML
    private TableColumn<UserRole, Enum> tableRole;

    UserDaoIntf usersService;
    UserFileServiceIntf usersFileService;
    ObservableList<User> oL = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cellUsersValueFactory();

        try {
            usersService = new UserDaoImpl();
            usersFileService = new UserFileServiceImpl();
            refreshList();
            clearForm();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCustomerController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private void refreshList() throws SQLException {
        tableview.getItems().clear();
        oL = FXCollections.observableArrayList(usersService.findAll());
        tableview.setItems(oL);
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {
        int id = Integer.parseInt(tfUserId.getText());
        String userName = tfUserName.getText();
        User usersId = null;
        List<User> usersName = null;
        try {
            if (id > 0) {
                usersId = usersService.findById(id);
                fillForm(usersId);
            } else if (userName != null) {
                usersName = usersService.findAll(userName, true);
                showSearchResult(usersName);
            } else {
                showMessage(Alert.AlertType.ERROR,
                        "Căutare",
                        "MESAJ INFORMATIONAL",
                        "Indicați metoda de cautare <<ID>> sau <<NUME, PRENUME>>");
            }
        } catch (SQLException ex) {
            showMessage(Alert.AlertType.ERROR,
                    "Căutare",
                    "MESAJ INFORMATIONAL",
                    "Utilizatorul cu id-ul, " + id
                    + " nu a fost gasit \n" + ex.getMessage());
        }
    }

    private void showSearchResult(List<User> userName) {

        if (userName.size() == 0) {
            showMessage(Alert.AlertType.ERROR,
                    "Căutare",
                    "MESAJ INFORMATIONAL",
                    "Utilizatorul cu numele, *"
                    + tfUserName.getText().toUpperCase() + "* nu exista");
        } else if (userName.size() == 1) {
            fillForm(userName.get(0));
            System.out.println("Utilizatorul a fost gasit");
        } else {
            showMessage(Alert.AlertType.WARNING, "Căutare",
                    "MESAJ INFORMATIONAL",
                    "Utilizatorul cu numele, " + tfUserName.getText().toUpperCase()
                    + " a fost adăugat în baza de date de mai multe ori. "
                    + "Te rog, alege utilizatorul manual din lista de mai jos.");
            System.out.println("Au fost gasiti mai multi utilizatori de același tip");
        }
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        try {
            User user = readForm();
            usersService.save(user);
            showMessage(Alert.AlertType.INFORMATION,
                    "Înregistrare",
                    "MESAJ INFORMATIONAL",
                    "Utilizatorul cu id-ul " + user.getId()
                    + " a fost inregistrat cu succes");
            clearForm();
            refreshList();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCustomerController.class.getName()).
                    log(Level.SEVERE, null, ex);
            showMessage(Alert.AlertType.ERROR,
                    "Înregistrare",
                    "MESAJ INFORMATIONAL",
                    "Eroare la salvare \n" + ex.getMessage());
        }
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) {
        int id = Integer.parseInt(tfUserId.getText());
        User userF = readForm();

        try {
            User userDB = usersService.findById(id);
            if (userDB != null) {

                userDB.setName(userF.getName());
                userDB.setLogin(userF.getLogin());
                userDB.setPassword(userF.getPassword());
                userDB.setUserRole(userF.getUserRole());

                usersService.update(userDB);
                showMessage(Alert.AlertType.INFORMATION,
                        "Actualizare",
                        "MESAJ INFORMATIONAL",
                        "Utilizatorul cu numele *" + userDB.getName().toUpperCase()
                        + "*, a fost actualizat cu succes");
                clearForm();
                refreshList();
            }
        } catch (SQLException ex) {
            showMessage(Alert.AlertType.ERROR,
                    "Actualizare",
                    "MESAJ INFORMATIONAL",
                    "Eroare la actualizarea utilizatorului \n" + ex.getMessage());
        }
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        clearForm();
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        int id = Integer.parseInt(tfUserId.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ștergere");
        alert.setHeaderText("ATENȚIE!");
        alert.setContentText("Ești sigur că vrei să ștergi"
                + " utilizatorul din listă?");

        ButtonType yes = new ButtonType("Da");
        ButtonType no = new ButtonType("Nu");
        alert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            try {
                User customerDB = usersService.findById(id);
                if (customerDB != null) {
                    usersService.delete(customerDB);
                    showMessage(Alert.AlertType.INFORMATION,
                            "Ștergere",
                            "MESAJ INFORMATIONAL",
                            "Utilizatorul *" + customerDB.getName().toUpperCase()
                            + " a fost șters din listă");
                    clearForm();
                    refreshList();
                }
            } catch (SQLException ex) {
                showMessage(Alert.AlertType.ERROR,
                        "Ștergere",
                        "MESAJ INFORMATIONAL",
                        "Eroare în procesul de ștergere \n" + ex.getMessage());
            }
        } else if (result.get() == no) {
            alert.close();
        }
    }

    @FXML
    private void handleExcelExportAction(ActionEvent event) {
        String fileName = "utilizatori_" + LocalDateTime.now().
                format(DateTimeFormatter.ISO_DATE) + ".xlsx";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Export utilizatori EXCEL");
        alert.setHeaderText("ATENȚIE!");
        alert.setContentText("Vrei să exporți datele clienților?");

        ButtonType yes = new ButtonType("Da");
        ButtonType no = new ButtonType("Nu");
        alert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yes) {
            try {
                usersFileService.scrie(oL, fileName);
                showMessage(Alert.AlertType.INFORMATION,
                        "Utilizatori",
                        "MESAJ INFORMATIONAL",
                        "Informația a fost exportată cu succes în fișierul, " + fileName);
            } catch (IOException ex) {
                Logger.getLogger(FXMLCustomerController.class.getName()).
                        log(Level.SEVERE, null, ex);
                showMessage(Alert.AlertType.ERROR,
                        "Utilizatori",
                        "MESAJ INFORMATIONAL",
                        "Eroare la export \n" + ex.getMessage());
            }
        } else if (result.get() == no) {
            alert.close();
        }
    }

    @FXML
    private void handlePdfExportAction(ActionEvent event) {
        String fileName = "utilizatori_" + LocalDateTime.now().
                format(DateTimeFormatter.ISO_DATE) + ".pdf";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Export utilizatori în PDF");
        alert.setHeaderText("ATENȚIE!");
        alert.setContentText("Vrei să exporți datele clienților?");

        ButtonType yes = new ButtonType("Da");
        ButtonType no = new ButtonType("Nu");
        alert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yes) {
            try {
                usersFileService.creaza(oL, fileName);
                showMessage(Alert.AlertType.INFORMATION,
                        "Utilizatori",
                        "MESAJ INFORMATIONAL",
                        "Informația a fost exportată cu succes în fișierul, " + fileName);
            } catch (IOException ex) {
                Logger.getLogger(FXMLCustomerController.class.getName()).
                        log(Level.SEVERE, null, ex);
                showMessage(Alert.AlertType.ERROR,
                        "Utilizatori",
                        "MESAJ INFORMATIONAL",
                        "Eroare la export \n" + ex.getMessage());
            }
        } else if (result.get() == no) {
            alert.close();
        }
    }

    @FXML
    private void handleActionUserMouseClicked(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            fillForm(tableview.getFocusModel().getFocusedItem());
        }
    }

    @Override
    public void fillForm(User u) {
        UserRole ur = u.getUserRole();

        tfUserId.setText("" + u.getId());
        tfUserName.setText(u.getName());
        tfLoghin.setText(u.getLogin());
        tfPassword.setText(u.getPassword());
        tfusersRoles.setValue(ur);
    }

    @Override
    public User readForm() {
        User u = new User();

        u.setId(Integer.parseInt(tfUserId.getText()));
        u.setName(tfUserName.getText());
        u.setLogin(tfLoghin.getText());
        u.setPassword(tfPassword.getText());
        u.setUserRole(tfusersRoles.getValue());

        return u;
    }

    @Override
    public void clearForm() {
        tfUserId.setText("0");
        tfUserName.setText("");
        tfLoghin.setText("");
        tfPassword.setText("");
        tfusersRoles.setValue(UserRole.OPERATOR);
    }

    private void showMessage(Alert.AlertType type, String title, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    private void hashingPassword() {
        String password = tfPassword.getText();

        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);

            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte b : hashedPassword) {
                builder.append(String.format("%02X", b));
            }
            System.out.println(builder.toString());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLUserController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private void cellUsersValueFactory() {
        // crearea tabelei de date
        tableId.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        tableLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        tableRole.setCellValueFactory(new PropertyValueFactory<UserRole, Enum>("userRole"));

        //Initierea tabelei cu coloanele indicate
        tableview.setItems(oL);

        //Initierea rolurilor in comboBox
        tfusersRoles.setItems(FXCollections.observableArrayList(UserRole.values()));
    }
}
