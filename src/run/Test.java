package run;

import model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class Test {

    public static void main(String[] args) {

        System.out.println("select medicineId, name, sum(quantity), unitOfMeasure, invoiceDate from orderTable join medicine " +
                "on orderTable.medicineId = medicine.id join invoice " +
                "on orderTable.invoiceId = invoice.id where invoice.invoiceDate >= " + "'" + "123123123" + "'"
                + " and invoice.invoiceDate <= " + "'" + "123123123" + "'" + " group by name");

        Medicine m;
        PillForm p = new PillForm();

        m = p;
        m.getTYPE();
    }
}
