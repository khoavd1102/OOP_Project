package services;

import model.OrderTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderTableSevices {
    public void add(int invoiceId, int medicineId, int quantity, double amount) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        String insert = "INSERT INTO orderTable(invoiceId, medicineId, quantity, amount) values" +
                "(" + invoiceId + ", " + medicineId + ", " + quantity + ", " + amount + ")";
        Statement statement = conn.createStatement();
        statement.execute(insert);
        statement.close();
        conn.close();
    }

    public List<OrderTableModel> getDetails(int id) throws SQLException {
        List<OrderTableModel> orderTableModelList = new ArrayList<>();

        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String get = "SELECT medicineId, name, quantity, price, amount FROM orderTable join medicine " +
                "on orderTable.medicineId = medicine.id WHERE invoiceId = " + id;
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(get);

        while(resultSet.next()){
            orderTableModelList.add(new OrderTableModel(resultSet.getInt("medicineId"), resultSet.getString("name"),
                    resultSet.getInt("quantity"), resultSet.getDouble("price"), resultSet.getDouble("amount")));
        }

        statement.close();
        conn.close();
        return orderTableModelList;
    }
}
