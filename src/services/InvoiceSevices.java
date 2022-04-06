package services;

import model.InvoiceModel;
import model.OrderTableModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceSevices {
    public void add(InvoiceModel invoiceModel) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        String insert = "INSERT INTO invoice(description, invoiceDate, total) values" +
                "('" + invoiceModel.getDescription() + "', '" + invoiceModel.getInvoiceDate().toString()
                + "'," + invoiceModel.getTotal() + ")";

        Statement statement = conn.createStatement();
        statement.execute(insert);
        statement.close();
        conn.close();
    }

    public InvoiceModel getInvoice() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        String select = "SELECT * FROM invoice ORDER BY id DESC LIMIT 1";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        resultSet.next();
        InvoiceModel invoiceModel = new InvoiceModel(resultSet.getInt("id"), resultSet.getString("description"),
                resultSet.getDate("invoiceDate"), resultSet.getDouble("total"));
        statement.close();
        conn.close();
        return invoiceModel;
    }

    public List<InvoiceModel> getInvoiceList() throws SQLException {
        List<InvoiceModel> invoiceList = new ArrayList<>();

        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String get = "SELECT * FROM invoice";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(get);

        while (resultSet.next()) {
            invoiceList.add(new InvoiceModel(resultSet.getInt("id"), resultSet.getString("description"),
                    resultSet.getDate("invoiceDate"), resultSet.getDouble("total")));
        }
        statement.close();
        conn.close();
        return invoiceList;
    }

    public List<OrderTableModel> getStatistics(Date from, Date to) throws SQLException {
        List<OrderTableModel> statisticsList = new ArrayList<>();

        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String get = "select medicineId, name, sum(quantity), unitOfMeasure, sum(amount), invoiceDate from orderTable join medicine " +
                "on orderTable.medicineId = medicine.id join invoice " +
                "on orderTable.invoiceId = invoice.id where invoice.invoiceDate >= " + "'" + from.toString() + "'"
                + " and invoice.invoiceDate <= " + "'" + to.toString() + "'" + " group by name";
        System.out.println("hihih");
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(get);

        while (resultSet.next()) {
            statisticsList.add(new OrderTableModel(resultSet.getInt("medicineId"), resultSet.getString("name"),
                    resultSet.getInt("sum(quantity)"), resultSet.getString("unitOfMeasure"),
                    resultSet.getDouble("sum(amount)"), resultSet.getDate("invoiceDate")));
        }
        statement.close();
        conn.close();
        return statisticsList;
    }
}
