package controller.productController;

import services.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import services.MedicineSevices;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddController implements Initializable {
    @FXML
    private Button cancelBtn;

    @FXML
    private TextArea discriptionTextArea;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private Button saveBtn;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    private String[] typeOfMedicine = {"PillForm", "Powder", "Tuber", "Leaves", "Trunk"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChoiceBox.getItems().addAll(typeOfMedicine);
    }

    //close form when click cancel
    public void cancelBtnOnAction(ActionEvent envent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //save information
    public void saveBtnOnAction(ActionEvent event) throws SQLException, IOException {
        Pattern pattern;

        //check name format
        if (nameTextField.getText().length() < 1 || nameTextField.getText().length() > 50) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Name exceeds the allowed number of characters," +
                    "medicine name must be from 1 to 50 characters.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        //check price format
        pattern = Pattern.compile("^[1-9]\\d*(\\.\\d+)?$");
        if (priceTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter the price of medicine.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        } else if(!pattern.matcher(priceTextField.getText()).matches()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter only numbers in price field.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        //check type
        if (typeChoiceBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select the type of medicine.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        // check date
        if (expiryDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter the expiry date.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        String name = nameTextField.getText();
        Double price = Double.parseDouble(priceTextField.getText());
        String type = typeChoiceBox.getValue();
        Date expiryDate = Date.valueOf(expiryDatePicker.getValue());
        String discription = discriptionTextArea.getText();

        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();


//        Medicine medicine = new Medicine(name, price, expiryDate, discription);
        // PillForm p = (PillForm) medicine;
        Medicine medicine;
        if (type.equalsIgnoreCase(new PillForm().getTYPE())) {
            medicine = new PillForm(name, price, expiryDate, discription);
            new MedicineSevices().add(medicine);
        } else if (type.equalsIgnoreCase(new PowderMedicine().getTYPE())) {
            medicine = new PowderMedicine(name, price, expiryDate, discription);
            new MedicineSevices().add(medicine);
        } else if (type.equalsIgnoreCase(new TrunkMedicine().getTYPE())) {
            medicine = new TrunkMedicine(name, price, expiryDate, discription);
            new MedicineSevices().add(medicine);
        } else if (type.equalsIgnoreCase(new LeavesMedicine().getTYPE())) {
            medicine = new LeavesMedicine(name, price, expiryDate, discription);
            new MedicineSevices().add(medicine);
        } else if (type.equalsIgnoreCase(new TuberMedicine().getTYPE())) {
            medicine = new TuberMedicine(name, price, expiryDate, discription);
            new MedicineSevices().add(medicine);
        }
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
}
