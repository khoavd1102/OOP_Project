package controller.productController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
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
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UpdateController implements Initializable {
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

    private Medicine medicine;
    private int id;

    public Medicine getMedicine(Medicine medicine) {
        return medicine;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChoiceBox.getItems().addAll(typeOfMedicine);
    }

    public void cancelBtnOnAction(ActionEvent envent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
        this.id = medicine.getId();
        nameTextField.setText(medicine.getName());
        priceTextField.setText(Double.toString(medicine.getPrice()));
        typeChoiceBox.setValue(medicine.getTYPE());
        expiryDatePicker.setValue(medicine.getExpiryDate().toLocalDate());
        discriptionTextArea.setText(medicine.getDescription());
    }

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
        } else if (!pattern.matcher(priceTextField.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Price number is not valid. Please try again", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }


        //select type check
        if (typeChoiceBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select the type of medicine.", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        //check select date
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

        if (type.equalsIgnoreCase(new PillForm().getTYPE())) {
            medicine = new PillForm(id, name, price, expiryDate, discription);
//            new MedicineSevives().update(medicine);
            System.out.println(medicine.getId());

        } else if (type.equalsIgnoreCase(new PowderMedicine().getTYPE())) {
            medicine = new PowderMedicine(id, name, price, expiryDate, discription);
//            new MedicineSevives().update(medicine);
            System.out.println(medicine.getId());

        } else if (type.equalsIgnoreCase(new TrunkMedicine().getTYPE())) {
            medicine = new TrunkMedicine(id, name, price, expiryDate, discription);
//            new MedicineSevives().update(medicine);
            System.out.println(medicine.getId());

        } else if (type.equalsIgnoreCase(new LeavesMedicine().getTYPE())) {
            medicine = new LeavesMedicine(id, name, price, expiryDate, discription);
//            new MedicineSevives().update(medicine);
            System.out.println(medicine.getId());

        } else if (type.equalsIgnoreCase(new TuberMedicine().getTYPE())) {
            medicine = new TuberMedicine(id, name, price, expiryDate, discription);
            System.out.println(medicine.getId());
//            new MedicineSevives().update(medicine);
        }

        new MedicineSevices().update(medicine);
        FXMLLoader fxmlLoader = new FXMLLoader(ProductManagementController.class.getResource("/ProductManagementView.fxml"));
        Parent parent = fxmlLoader.load();
        ProductManagementController productManagementController = fxmlLoader.getController();
        productManagementController.showMedicine();
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
}
