package controller.invoiceController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Medicine;
import model.OrderTableModel;
import services.InvoiceSevices;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    @FXML
    private TableColumn<OrderTableModel, Double> amountCol;

    @FXML
    private Button button;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private TableColumn<OrderTableModel, Date> invoiceDateCol;

    @FXML
    private TableColumn<OrderTableModel, Integer> medicineIdCol;

    @FXML
    private TableColumn<OrderTableModel, String> nameCol;

    @FXML
    private TableColumn<OrderTableModel, Integer> quantityCol;

    @FXML
    private TableView<OrderTableModel> statisticsTableView;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private TableColumn<OrderTableModel, String> unitOfMeasureCol;

    ObservableList<OrderTableModel> orderTableModelObservableList;
    private List<OrderTableModel> orderTableModelList;

    // initialize table
    public void init(){
        medicineIdCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, String>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Integer>("quantity"));
        unitOfMeasureCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, String>("unitOfMeasure"));
        amountCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Double>("amount"));
        invoiceDateCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Date>("invoiceDate"));
    }

    //Action when click to button
    public void buttonOnAction() throws SQLException {
        orderTableModelList = new InvoiceSevices().getStatistics(Date.valueOf(fromDatePicker.getValue()), Date.valueOf(toDatePicker.getValue()));
        orderTableModelObservableList = FXCollections.observableArrayList(orderTableModelList);
        statisticsTableView.setItems(orderTableModelObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
}
