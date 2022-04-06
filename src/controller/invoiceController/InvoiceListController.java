package controller.invoiceController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.InvoiceModel;
import services.InvoiceSevices;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceListController implements Initializable {
    @FXML
    TableColumn<InvoiceModel, Date> dateCol;

    @FXML
    TableColumn<InvoiceModel, Integer> idCol;

    @FXML
    TableView<InvoiceModel> invoiceTable;

    @FXML
    TableColumn<InvoiceModel, String> descriptionCol;

    @FXML
    TableColumn<InvoiceModel, Double> totalCol;

    ObservableList<InvoiceModel> invoiceModelObservableList;

    private List<InvoiceModel> invoiceModelList;


    public void showInvoice() throws SQLException {
        invoiceModelList = new InvoiceSevices().getInvoiceList(); //get invoice list from database
        invoiceModelObservableList = FXCollections.observableArrayList(invoiceModelList);

        idCol.setCellValueFactory(new PropertyValueFactory<InvoiceModel, Integer>("id"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<InvoiceModel, String>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<InvoiceModel, Date>("invoiceDate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<InvoiceModel, Double>("total"));
        invoiceTable.setItems(invoiceModelObservableList); // add all items to table
    }


    public void seeDetailsBtnOnAction() throws IOException, SQLException {
        InvoiceModel invoiceModel = invoiceTable.getSelectionModel().getSelectedItem();
        if (invoiceModel == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select the invoice you want to show!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(InvoiceListController.class.getResource("/InvoiceDetailsView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 491, 349);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        InvoiceDetailsController invoiceDetailsController = fxmlLoader.getController();
        invoiceDetailsController.setId(invoiceModel.getId());
        invoiceDetailsController.showDetailsTable(invoiceModel.getInvoiceDate(), invoiceModel.getTotal());
        stage.show();
    }

    public void seeStatisticsOnAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InvoiceListController.class.getResource("/StatisticsView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showInvoice();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
