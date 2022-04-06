package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Medicine;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    @FXML
    Button productManagementBtn = new Button();
    @FXML
    Button invoiceListBtn = new Button();
    @FXML
    Button sellBtn = new Button();
    @FXML
    Pane rightPane = new Pane();

    //show product management
    public void productManagementBtnOnAction(ActionEvent event) throws IOException, SQLException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/ProductManagementView.fxml"));
        rightPane.getChildren().removeAll();
        rightPane.getChildren().setAll(fxmlLoader);
    }

    //show invoice list
    public void invoiceListBtnOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/InvoiceListView.fxml"));
        rightPane.getChildren().removeAll();
        rightPane.getChildren().setAll(fxmlLoader);
    }

    //show sell view
    public void sellBtnOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SellController.class.getResource("/SellView.fxml"));
//        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/SellView.fxml"));
        Parent fxml = fxmlLoader.load();
        SellController sellController = fxmlLoader.getController();
        rightPane.getChildren().removeAll();
        rightPane.getChildren().setAll(fxml);
        rightPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    sellController.pressEnter(event);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
