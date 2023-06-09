package emr.consultationsheetapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static emr.consultationsheetapp.App.transition;

public class LoginpageController {
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;


    @FXML
    void login(ActionEvent event) throws IOException {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        UserDAO user = new UserDAO(username, password);
        int userId = user.getUserId();
        if (userId == -1) {
            loginMessageLabel.setText("Invalid Login, Please try again!");
        } else if ( userId == 0) {
            openAdminPage();
        } else {
            openDoctorPage(user.getClinic());
        }
    }

    private void openAdminPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminpatientpage-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginButton.getScene().getWindow();
        transition(root, scene, stage, "AdminPatient e-ConsultationSheet");
    }

    private void openDoctorPage(int clinic) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorasesmentpage-view.fxml"));
        Parent root = loader.load();
        DoctorAssesmentController doctorAssesmentController = loader.getController();
        doctorAssesmentController.receiveClinic(clinic);
        doctorAssesmentController.showTableItems();
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginButton.getScene().getWindow();
        transition(root, scene, stage, "Admin Doctor e-ConsultationSheet");
    }




}