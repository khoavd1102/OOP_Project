package controller.productController;

import controller.productController.AddController;
import controller.productController.UpdateController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Medicine;
import services.MedicineSevices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class ProductManagementController implements Initializable {
    @FXML
    Button addBtn = new Button();
    @FXML
    Button updateBtn = new Button();
    @FXML
    Button deleteBtn = new Button();
    @FXML
    Button moreDetailBtn = new Button();
    @FXML
    TextField searchTextField;
    @FXML
    TableView<Medicine> medicineTableView;
    @FXML
    TableColumn<Medicine, Integer> idMedicineTableViewCol;
    @FXML
    TableColumn<Medicine, String> nameMedicineTableViewCol;
    @FXML
    TableColumn<Medicine, Double> priceMedicineTableViewCol;
    @FXML
    TableColumn<Medicine, String> typeMedicineTableViewCol;
    @FXML
    TableColumn<Medicine, String> descriptionMedicineTableViewCol;

    ObservableList<Medicine> listValueTableView;
    private List<Medicine> medicineList;

    public void showMedicine() throws SQLException {
        medicineList = new MedicineSevices().getListMedicine(); // get items from database
        listValueTableView = FXCollections.observableArrayList(medicineList);
        Map<Integer, String> typeMap = new HashMap<>();
        medicineList.forEach(medicine -> {
            typeMap.put(medicine.getId(), medicine.getTYPE());
        });
        idMedicineTableViewCol.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("id"));
        nameMedicineTableViewCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        priceMedicineTableViewCol.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("price"));
        typeMedicineTableViewCol.setCellValueFactory((TableColumn.CellDataFeatures<Medicine, String> p)
                -> new ReadOnlyStringWrapper(
                typeMap.get(p.getValue().getId()).toString()));
        descriptionMedicineTableViewCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("description"));

        medicineTableView.setItems(listValueTableView); //add items to the tableview
    }

    //action when click add
    public void addBtnOnAction(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddController.class.getResource("/AddView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.showAndWait();
        showMedicine();
        search();
    }

    //action when click delete
    public void deleteBtnOnAction(ActionEvent event) throws SQLException {
        Medicine medicine = medicineTableView.getSelectionModel().getSelectedItem();

        //check select items
        if (medicine == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select the medicine you want to delete!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure to delete this medicine!",
                    ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.NO) {
                return;
            } else {
                new MedicineSevices().del(medicine.getId());
            }
        }
        showMedicine();
        search();
    }

    //action when click update
    public void updateBtnOnAction(ActionEvent event) throws SQLException, IOException {
        Medicine medicine = medicineTableView.getSelectionModel().getSelectedItem();

        //check select items
        if (medicine == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select the medicine you want to update!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(UpdateController.class.getResource("/UpdateView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        UpdateController updateController = fxmlLoader.getController();
        updateController.setMedicine(medicine);
        stage.showAndWait();
        showMedicine();
        search();
    }
    //action when click moredetails
    public void moreDetailBtnOnAction(ActionEvent event) {
        Medicine medicine = medicineTableView.getSelectionModel().getSelectedItem();
        //check select items
        if (medicine == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select the medicine you want to see!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            //show infor
            String information = "ID: " + medicine.getId() + "\nName: " + medicine.getName() + "\nPrice: " + medicine.getPrice()
                    + "\nExpiry date: " + medicine.getExpiryDate() + "\nType: " + medicine.getTYPE() + "\nUnit of medicine: "
                    + medicine.getUNIT_OF_MEASURE() + "\nDescription: "
                    + medicine.getDescription();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, information, ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        search();
    }

    //initialize search
    public void search() {
        FilteredList<Medicine> medicineFilteredList = new FilteredList<>(listValueTableView, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            medicineFilteredList.setPredicate(medicine -> {
                //if no search value then display all records or whatever records it current have. no changes.
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchString = newValue.toLowerCase();

                if (medicine.getName().toLowerCase().indexOf(searchString) > -1) {
                    return true; //Match in name
                } else if (String.valueOf(medicine.getId()).indexOf(searchString) > -1) {
                    return true; //match in id
                } else if (String.valueOf(medicine.getPrice()).indexOf(searchString) > -1) {
                    return true;
                } else if (medicine.getDescription().toLowerCase().indexOf(searchString) > -1) {
                    return true;
                } else if (medicine.getTYPE().toLowerCase().indexOf(searchString) > -1) {
                    return true;
                } else{
                    return false;
                }
            });
        });
        SortedList<Medicine> medicineSortedList = new SortedList<>(medicineFilteredList);

        medicineSortedList.comparatorProperty().bind(medicineTableView.comparatorProperty());

        medicineTableView.setItems(medicineSortedList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showMedicine();
            search();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
