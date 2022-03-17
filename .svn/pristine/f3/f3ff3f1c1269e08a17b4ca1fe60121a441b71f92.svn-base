package com.iucosoft.beautysalon.view;

import com.iucosoft.beautysalon.dao.CustomerDaoIntf;
import com.iucosoft.beautysalon.dao.RegistrationDaoIntf;
import com.iucosoft.beautysalon.dao.UserDaoIntf;
import com.iucosoft.beautysalon.dao.impl.CustomerDaoImpl;
import com.iucosoft.beautysalon.dao.impl.RegistrationDaoImpl;
import com.iucosoft.beautysalon.dao.impl.UserDaoImpl;
import com.iucosoft.beautysalon.models.Customer;
import com.iucosoft.beautysalon.models.OperationStatus;
import com.iucosoft.beautysalon.models.OperationType;
import com.iucosoft.beautysalon.models.Operation;
import com.iucosoft.beautysalon.models.User;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Rusanovschi
 */
public class FXMLReportController implements Initializable, FormularIntf<Operation> {

    @FXML
    private TableView<Operation> tableView;
    @FXML
    private TableColumn<Operation, Enum> tableServiciu;
    @FXML
    private TableColumn<Operation, Enum> tableStatus;
    @FXML
    private TableColumn<Operation, LocalDate> tableData;
    @FXML
    private TableColumn<Operation, String> tableCustomers;
    @FXML
    private TableColumn<Operation, String> tableUsers;
    @FXML
    private DatePicker dpFromDate;
    @FXML
    private ComboBox<OperationStatus> cbStatus;
    @FXML
    private ComboBox<Customer> cbCustomerName;
    @FXML
    private ComboBox<User> cbUserName;
    @FXML
    private DatePicker dpToDate;
    @FXML
    private ComboBox<OperationType> cbOperation;

    RegistrationDaoIntf registrationService;
    ObservableList<Operation> obsL = FXCollections.observableArrayList();
    String filter = "";
    ObservableList<Customer> customersItems;
    ObservableList<User> usersItems;

    CustomerDaoIntf customerService;
    UserDaoIntf userService;
    private static final Logger LOG = Logger.getLogger(FXMLReportController.class.getName());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            registrationService = new RegistrationDaoImpl();
            customerService = new CustomerDaoImpl();
            userService = new UserDaoImpl();

            List<Customer> listCustomers = customerService.findAll();
            List<User> listUsers = userService.findAll();

            customersItems = FXCollections.observableArrayList(listCustomers);
            usersItems = FXCollections.observableArrayList(listUsers);
            cbUserName.setItems(usersItems);
            cbCustomerName.setItems(customersItems);

            try {
                refreshList();
                clearForm();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLCustomerController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLReportController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private void refreshList() throws SQLException {
        tableView.getItems().clear();
        obsL = FXCollections.observableArrayList(registrationService.getOperations());
        tableView.setItems(obsL);
    }

    private void refreshListReport(LocalDate d1, LocalDate d2,
            OperationType oType, OperationStatus oStatus) throws SQLException {

        List<Operation> list = registrationService.getOperation(d1, d2, oType, oStatus);
        obsL = FXCollections.observableArrayList(list);
        tableView.setItems(obsL);
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {

        LocalDate dateFrom = dpFromDate.getValue();
        LocalDate dateTo = dpToDate.getValue();
        OperationType oType = cbOperation.getValue();
        OperationStatus oStatus = cbStatus.getValue();

        try {
            refreshListReport(dateFrom, dateTo, oType, oStatus);
            if (obsL.size() > 0) {
                cellOperationsValueFactory();
            } else {
                showMessage(Alert.AlertType.ERROR,
                        "Rapoart de înregistrări",
                        "MESAJ INFORMATIONAL",
                        "Înregistrările nu au fost găsite, "
                        + "te rog filtrează corect informația");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLReportController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        clearForm();
    }

    @Override
    public void fillForm(Operation o) {
        Customer c = o.getCustomer();
        User u = o.getUser();
        OperationType oT = o.getOperationType();
        OperationStatus oS = o.getOperationStatus();
        LocalDate ldFrom = o.getLocalDate();
        LocalDate ldTo = o.getLocalDate();

        cbCustomerName.setValue(c);
        cbUserName.setValue(u);
        cbOperation.setValue(oT);
        cbStatus.setValue(oS);
        dpFromDate.setValue(ldFrom);
        dpToDate.setValue(ldTo);
    }

    @Override
    public Operation readForm() {
        return null;
    }

    @Override
    public void clearForm() {

        tableView.getItems().clear();
        cbCustomerName.setValue(null);
        cbUserName.setValue(null);
        cbOperation.setValue(null);
        cbStatus.setValue(null);
        dpFromDate.setValue(null);
        dpToDate.setValue(null);
    }

    @FXML
    public void handleOnKeyPressed(KeyEvent e) {

        cbCustomerName.setTooltip(new Tooltip());
        cbCustomerName.getTooltip().setText("Please enter after remove text..");

//        List<Customer> list = new ArrayList<>();
        ObservableList<Customer> filteredList;
//        ObservableList<Customer> filteredList = FXCollections.observableArrayList(list);

        customersItems = FXCollections.observableArrayList();

        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();

        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            cbCustomerName.getItems().setAll(customersItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (code == KeyCode.ENTER) {
            filter = "";
        }
        if (filter.length() == 0) {
            try {
                List<Customer> listCustomers = customerService.findAll();

                customersItems = FXCollections.observableArrayList(listCustomers);
                cbCustomerName.setItems(customersItems);

            } catch (SQLException ex) {
                LOG.severe(ex.getMessage());
            }
        } else {

            try {

                filteredList = FXCollections.observableArrayList();

//              Stream<Customer> items = tfCustomerName.getItems().stream();
                String txtUsr = filter.toLowerCase();

                System.out.println("TxtUser = " + txtUsr);

                filteredList.setAll(customerService.findByName(filter));
                cbCustomerName.show();
                cbCustomerName.getItems().setAll(filteredList);

            } catch (SQLException ex) {
                LOG.severe(ex.getMessage());
            }
        }
    }

    @FXML
    private void handleOnHiding(Event e) {
        filter = "";
        Customer s = cbCustomerName.getSelectionModel().getSelectedItem();

        if (s != null) {
            cbCustomerName.getItems().setAll(customersItems);
            cbCustomerName.getSelectionModel().select(s);
        }
    }

    @FXML
    private void handleOnKeyPressedUser(KeyEvent event) {
        cbUserName.setTooltip(new Tooltip());
        cbUserName.getTooltip().setText("Please enter after remove text..");

        ObservableList<User> filteredList;
        customersItems = FXCollections.observableArrayList();

        KeyCode code = event.getCode();

        if (code.isLetterKey()) {
            filter += event.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            cbUserName.getItems().setAll(usersItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (code == KeyCode.ENTER) {
            filter = "";
        }
        if (filter.length() == 0) {
            try {
                List<User> listUsers = userService.findAll();

                usersItems = FXCollections.observableArrayList(listUsers);
                cbUserName.setItems(usersItems);

            } catch (SQLException ex) {
                LOG.severe(ex.getMessage());
            }
        } else {

            try {

                filteredList = FXCollections.observableArrayList();

                String txtUsr = filter.toLowerCase();

                System.out.println("TxtUser = " + txtUsr);

                filteredList.setAll(userService.findByName(filter));
                cbUserName.show();
                cbUserName.getItems().setAll(filteredList);

            } catch (SQLException ex) {
                LOG.severe(ex.getMessage());
            }
        }
    }

    @FXML
    private void handleOnHidingUser(Event event) {
        filter = "";
        User u = cbUserName.getSelectionModel().getSelectedItem();

        if (u != null) {
            cbUserName.getItems().setAll(usersItems);
            cbUserName.getSelectionModel().select(u);
        }
    }

    private void showMessage(Alert.AlertType type, String title, String headerText, String contentText) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    private void cellOperationsValueFactory() {
        // crearea tabelei de date
        tableCustomers.setCellValueFactory(new PropertyValueFactory<Operation, String>("customer"));
        tableUsers.setCellValueFactory(new PropertyValueFactory<Operation, String>("user"));
        tableServiciu.setCellValueFactory(new PropertyValueFactory<Operation, Enum>("operationType"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<Operation, Enum>("operationStatus"));
        tableData.setCellValueFactory(new PropertyValueFactory<Operation, LocalDate>("localDate"));

        //Initierea in comboBox
        cbOperation.setItems(FXCollections.observableArrayList(OperationType.values()));
        cbStatus.setItems(FXCollections.observableArrayList(OperationStatus.values()));
    }
}
