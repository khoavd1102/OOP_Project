package model;

import java.sql.Date;

public abstract class Medicine {
    private int id;
    private String name;
    private double price;
    private Date expiryDate;
    private String description;

    public Medicine() {
    }

    public Medicine(int id, String name, double price, Date expiryDate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.description = description;
    }

    public Medicine(String name, double price, Date expiryDate, String description){
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.description = description;
    }

    public Medicine(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Medicine(int id, String name){
        this.id = id;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String discription) {
        this.description = discription;
    }

    public abstract String getTYPE();

    public abstract String getUNIT_OF_MEASURE();
}
