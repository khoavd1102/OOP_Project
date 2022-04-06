package controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.InvoiceModel;
import model.Medicine;
import model.OrderTableModel;
import services.InvoiceSevices;
import services.MedicineSevices;
import services.OrderTableSevices;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SellController implements Initializable{
    @FXML
    Button addBtn;

    @FXML
    Button clearBtn;

    @FXML
    TextField medicineIdTextField;

    @FXML
    TextField medicineNameTextField;

    @FXML
    TableView<OrderTableModel> orderTableView;

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
    TextField amountTextField;

    @FXML
    Label totalLabel;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    Spinner<Integer> quantitySpinner;

    @FXML
    TextField unitOfMeasureTextField;

    @FXML
    Button removeBtn;

    ObservableList<OrderTableModel> orderTableModelObservableList;
    Medicine medicine;

    //press enter to auto fill medicine information
    public void pressEnter(KeyEvent event) throws SQLException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Integer id = Integer.parseInt(medicineIdTextField.getText());
            medicine = new MedicineSevices().getMedicine(id);
            if (medicine == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Medicine id not found!", ButtonType.OK);
                alert.setHeaderText(null);
                alert.showAndWait();
                medicineIdTextField.clear();
                medicineNameTextField.clear();
                setSpinner();
                amountTextField.clear();
                unitOfMeasureTextField.clear();
            } else {
                medicineNameTextField.setText(medicine.getName());
                double amount = quantitySpinner.getValue()*medicine.getPrice();
                amountTextField.setText(Double.toString(amount));
                unitOfMeasureTextField.setText(medicine.getUNIT_OF_MEASURE());
            }
        }
    }

//    initialize table
    public void iniTable(){
        medicineIdCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, String>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Integer>("quantity"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Double>("price"));
        amountCol.setCellValueFactory(new PropertyValueFactory<OrderTableModel, Double>("amount"));
        unitOfMeasureTextField.setEditable(false);
        medicineNameTextField.setEditable(false);
        amountTextField.setEditable(false);
    }

    //set Spinner value
    public void setSpinner(){
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999);
        spinnerValueFactory.setValue(1);
        quantitySpinner.setValueFactory(spinnerValueFactory);
    }

    //click add to add items to table view
    public void addBtnOnAction(){
        OrderTableModel orderTableModel = new OrderTableModel(Integer.parseInt(medicineIdTextField.getText()), medicineNameTextField.getText()
                , quantitySpinner.getValue(), medicine.getPrice(), Double.parseDouble(amountTextField.getText()));
        orderTableView.getItems().add(orderTableModel);
        totalLabel.setText("Total: " + String.valueOf(getTotal()));
    }

    //get total pay
    public double getTotal(){
        double total = 0;
        for (OrderTableModel orderTableModel : orderTableView.getItems()) {
            total += orderTableModel.getAmount();
        }
        return total;
    }

    // export invoice
    public void payBtnOnAction() throws SQLException {
        java.util.Date date1 = new java.util.Date();
        Date date = new Date(date1.getTime());
        InvoiceModel invoiceModel = new InvoiceModel(descriptionTextArea.getText(), date, getTotal());
        new InvoiceSevices().add(invoiceModel);
        addOrderTableToSQL();
    }

    //add order items to mysql
    public void addOrderTableToSQL(){
        OrderTableSevices orderTableSevices = new OrderTableSevices();
        ObservableList<OrderTableModel> orderTableModelObservableList = orderTableView.getItems();
        orderTableModelObservableList.forEach(order ->
                {
                    try {
                        orderTableSevices.add(getInvoiceId(), order.getId(), order.getQuantity(), order.getAmount());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                );
    }

    public int getInvoiceId() throws SQLException {
        InvoiceModel invoiceModel = new InvoiceSevices().getInvoice();
        return invoiceModel.getId();
    }

    //click spinner to increase amount
    public void clickSpinner() {
        quantitySpinner.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double amount = quantitySpinner.getValue()*medicine.getPrice();
                amountTextField.setText(Double.toString(amount));
            }
        });
    }

    //remove items from table view
    public void removeBtnOnAction(){
        OrderTableModel orderTableModel = orderTableView.getSelectionModel().getSelectedItem();
        orderTableView.getItems().remove(orderTableModel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSpinner();
        iniTable();
        clickSpinner();
    }
}
