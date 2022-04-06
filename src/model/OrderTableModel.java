package model;

import java.sql.Date;

/*ngày bán, loại dược liệu, số lượng, đơn giá, thành tiền.*/
public class OrderTableModel extends Medicine{
    private int quantity;
    private double amount;
    private Date invoiceDate;
    private String unitOfMeasure;

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OrderTableModel(int id, String name, int quantity, double price, double amount) {
        super(id, name, price);
        this.quantity = quantity;
        this.amount = amount;
    }

    public OrderTableModel(int id, String name, int quantity, String unitOfMeasure, double amount, Date invoiceDate) {
        super(id, name);
        this.quantity = quantity;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public String getTYPE() {
        return this.getTYPE();
    }

    @Override
    public String getUNIT_OF_MEASURE() {
        return  this.getUNIT_OF_MEASURE();
    }
    //    private int medicineId;
//    private String medicineName;
//    private int quantity;
//    private double unitPrice;
//    private double amount;
//
//    public int getMedicineId() {
//        return medicineId;
//    }
//
//    public void setMedicineIdId(int medicineId) {
//        this.medicineId = medicineId;
//    }
//
//    public String getMedicineName() {
//        return medicineName;
//    }
//
//    public void setMedicineName(String medicineName) {
//        this.medicineName = medicineName;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public OrderTableModel(int medicineId, String medicineName, int quantity, double unitPrice, double amount) {
//        this.medicineId = medicineId;
//        this.medicineName = medicineName;
//        this.quantity = quantity;
//        this.unitPrice = unitPrice;
//        this.amount = amount;
//    }
//
//    public OrderTableModel(int invoiceId, int medicineId, int quantity, double amount) {
//    }
}
