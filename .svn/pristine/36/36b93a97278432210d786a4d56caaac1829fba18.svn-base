package com.iucosoft.beautysalon;

import com.iucosoft.beautysalon.models.UserRole;
import com.iucosoft.beautysalon.view.FXMLMainViewController;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.iucosoft.beautysalon.view.util.PaneType;

/**
 *
 * @author iucosoftmain
 */
public class JavaMainView extends Application {

    private Stage primaryStage;
    private BorderPane root;
    private String userNameActiv;

    UserRole userRole;

    public JavaMainView(String userNameActiv) {
        this.userNameActiv = userNameActiv;
    }

    private void centrerPaneReplace(String viewFXMLName) {
        try {
            AnchorPane altPanou = (AnchorPane) FXMLLoader.load(getClass().getResource(viewFXMLName));
            root.setCenter(altPanou);
        } catch (IOException ex) {
            Logger.getLogger(JavaMainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectPane(PaneType CODUL_PANOU) {

        switch (CODUL_PANOU) {
            case CUSTOMER:
                centrerPaneReplace("view/FXMLCustomer.fxml");
                break;
            case USER:
                centrerPaneReplace("view/FXMLUser.fxml");
                break;
            case REGISTRATION:
                centrerPaneReplace("view/FXMLRegistration.fxml");
                break;
            case REPORT:
                centrerPaneReplace("view/FXMLReport.fxml");
                break;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        String fxml = "view/FXMLMainView.fxml";
        InputStream in = JavaMainView.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(JavaMainView.class.getResource(fxml));
        try {
            root = (BorderPane) loader.load(in);
        } finally {
            in.close();
        }
        FXMLMainViewController refFXMLMainViewController = (FXMLMainViewController) loader.getController();
        refFXMLMainViewController.setMyMain(this);// metoda inventata de mine
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Beauty Salon");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showCustomer() {
        selectPane(PaneType.CUSTOMER);
    }

    public void showUser() {
        selectPane(PaneType.USER);
    }

    public void showRegistration() {
        selectPane(PaneType.REGISTRATION);
    }

    public void showReport() {
        selectPane(PaneType.REPORT);
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = JavaMainView.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(JavaMainView.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        return (Initializable) loader.getController();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public String getUserNameActiv() {
        return userNameActiv;
    }

    public void setUserNameActiv(String userNameActiv) {
        this.userNameActiv = userNameActiv;
    }
}
