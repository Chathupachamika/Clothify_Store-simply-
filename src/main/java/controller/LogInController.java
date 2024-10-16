package controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Label forgetLink;

    @FXML
    private JFXRadioButton rdioRemember;

    @FXML
    private Label registerLink;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLogIn(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        boolean isAuthenticated = authenticateUser(email, password);

        if (isAuthenticated) {
            loadDashboard(event);
        } else {
            System.out.println("Invalid email or password");
        }
    }

    private boolean authenticateUser(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }
    private void loadDashboard(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"))));
            stage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSingUpGoogle(ActionEvent event) {
        show();
        System.out.println("Ewa hadala ne... thiyena widiyta blhn...");
        loadDashboard(event);

    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void show() {
        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "1. Turn on your MySQL terminal.\n" +
                        "2. Add the necessary SQL code related to the database\n in the test file attached in the folder.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show();
    }
}
