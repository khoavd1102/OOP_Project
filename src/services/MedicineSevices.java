package services;

import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicineSevices {
    public void add(Medicine medicine) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        String insert = "insert into medicine(name, price, unitOfMeasure, type, expiryDate, description)" +
                "values ('" + medicine.getName() + "','" + medicine.getPrice() + "','"
                + medicine.getUNIT_OF_MEASURE() + "', '" + medicine.getTYPE() + "','"
                + medicine.getExpiryDate().toString() + "','" + medicine.getDescription() + "')";

        Statement statement = conn.createStatement();
        statement.execute(insert);
        statement.close();
        conn.close();
    }

    public void del(int id) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        String delete = "DELETE FROM medicine WHERE medicine.id='" + id + "';";
        Statement statement = conn.createStatement();
        statement.execute(delete);
        statement.close();
        conn.close();
    }

    public void update(Medicine medicine) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        String update = "UPDATE medicine SET name=" + "'" + medicine.getName() + "', price=" + medicine.getPrice() + ","
                + " unitOfMeasure=" + "'" + medicine.getUNIT_OF_MEASURE() + "', type=" + "'" + medicine.getTYPE()
                + "', expiryDate=" + "'" + medicine.getExpiryDate().toString() + "', description=" + "'" + medicine.getDescription()
                + "' WHERE id=" + medicine.getId();
        Statement statement = conn.createStatement();
        statement.execute(update);
        statement.close();
        conn.close();
        System.out.printf("Update thanh cong");
    }

    public Medicine getMedicine(int id) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        String select = "SELECT * FROM medicine WHERE id =" + id;

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(select);

        Medicine medicine;
        if (!resultSet.next()) {
            return null;
        } else if (resultSet.getString(5).equalsIgnoreCase(new PillForm().getTYPE())) {
            medicine = new PillForm(resultSet.getInt("id"), resultSet.getString("name")
                    , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                    , resultSet.getString("description"));
            return medicine;
        } else if (resultSet.getString(5).equalsIgnoreCase(new PowderMedicine().getTYPE())) {
            medicine = new PowderMedicine(resultSet.getInt("id"), resultSet.getString("name")
                    , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                    , resultSet.getString("description"));
            return medicine;
        } else if (resultSet.getString(5).equalsIgnoreCase(new TrunkMedicine().getTYPE())) {
            medicine = new TrunkMedicine(resultSet.getInt("id"), resultSet.getString("name")
                    , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                    , resultSet.getString("description"));
            return medicine;
        } else if (resultSet.getString(5).equalsIgnoreCase(new LeavesMedicine().getTYPE())) {
            medicine = new LeavesMedicine(resultSet.getInt("id"), resultSet.getString("name")
                    , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                    , resultSet.getString("description"));
            return medicine;
        } else if (resultSet.getString(5).equalsIgnoreCase(new TuberMedicine().getTYPE())) {
            medicine = new TuberMedicine(resultSet.getInt("id"), resultSet.getString("name")
                    , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                    , resultSet.getString("description"));
            return medicine;
        }
        statement.close();
        conn.close();
        return null;
    }

    public List<Medicine> getListMedicine() throws SQLException {
        List<Medicine> medicineList = new ArrayList<>();

        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String get = "SELECT * FROM medicine";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(get);
        while (resultSet.next()) {
            if (resultSet.getString(5).equalsIgnoreCase(new PillForm().getTYPE())) {
                medicineList.add(new PillForm(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                        , resultSet.getString("description")));
            } else if (resultSet.getString(5).equalsIgnoreCase(new PowderMedicine().getTYPE())) {
                medicineList.add(new PowderMedicine(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                        , resultSet.getString("description")));
            } else if (resultSet.getString(5).equalsIgnoreCase(new TrunkMedicine().getTYPE())) {
                medicineList.add(new TrunkMedicine(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                        , resultSet.getString("description")));
            } else if (resultSet.getString(5).equalsIgnoreCase(new LeavesMedicine().getTYPE())) {
                medicineList.add(new LeavesMedicine(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                        , resultSet.getString("description")));
            } else if (resultSet.getString(5).equalsIgnoreCase(new TuberMedicine().getTYPE())) {
                medicineList.add(new TuberMedicine(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getDouble("price"), resultSet.getDate("expiryDate")
                        , resultSet.getString("description")));
            }
        }
        statement.close();
        conn.close();
        return medicineList;
    }

}
