package controller.invoiceController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderTableModel;
import services.OrderTableSevices;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceDetailsController implements Initializable {
    @FXML
    TableView<OrderTableModel> orderDetailsTableView;

    @FXML
    TableColumn<OrderTableModel, Integer> medicineIdCol;

    @FXML
    TableColumn<OrderTableModel, String> nameCol;

    @FXML
    TableColumn<OrderTableModel, Integer> quantityCol;

    @FXML
    TableColumn<OrderTableModel, Double> unitPriceCol;

    @FXML
    TableColumn<OrderTableModel, Double> amountCol;

    @FXML
    private Label invoiceDateLabel;

    @FXML
    private Label totalLabel;

    List<OrderTableModel> detailsList;

    ObservableList<OrderTableModel> orderTableModelObservableList;

    private int invoiceId;

    public void setId(int invoiceId) throws IOException {
        this.invoiceId = invoiceId;
    }
    //initialize table
    public void initTable(){
        medicineIdCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, String>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Integer>("quantity"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Double>("price"));
        amountCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Double>("amount"));
    }


    public void showDetailsTable(Date date, double total) throws SQLException, IOException {
        detailsList = new OrderTableSevices().getDetails(invoiceId);
        orderTableModelObservableList = FXCollections.observableArrayList(detailsList);
        orderDetailsTableView.setItems(orderTableModelObservableList); //add all items to tableview
        invoiceDateLabel.setText("Invoice date: " + date.toString()); //set date for label
        totalLabel.setText("Total: " + total); // set total for label
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
}
