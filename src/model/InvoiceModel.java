package model;

import java.sql.Date;
public class InvoiceModel {
    private int id;
    private String description;
    private Date invoiceDate;
    private double total;

    public InvoiceModel() {
    }

    public InvoiceModel(int id, String description, Date invoiceDate, double total) {
        this.id = id;
        this.description = description;
        this.invoiceDate = invoiceDate;
        this.total = total;
    }

    public InvoiceModel(String description, Date invoiceDate, double total) {
        this.description = description;
        this.invoiceDate = invoiceDate;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
