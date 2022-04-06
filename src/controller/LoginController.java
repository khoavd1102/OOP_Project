package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    public void loginBtnOnAction(ActionEvent event) throws IOException {
        String name = usernameTextField.getText();
        String pass = passwordTextField.getText();

        // check username and password
        if(!name.equals("admin") || !pass.equals("admin")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Username or password is incorrect!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        Parent home = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home,900,522));
        stage.setResizable(false);
        stage.show();
    }
}
