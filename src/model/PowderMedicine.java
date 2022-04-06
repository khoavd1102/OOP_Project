package model;

import java.sql.Date;

public class PowderMedicine extends Medicine{
    private final static String UNIT_OF_MEASURE = "Bag";
    private final static String TYPE = "Powder";

    public PowderMedicine() {
    }

    public PowderMedicine(int id, String name, double price, Date expiryDate, String discription) {
        super(id, name, price, expiryDate, discription);
    }

    public PowderMedicine(String name, double price, Date expiryDate, String discription) {
        super(name, price, expiryDate, discription);
    }

    @Override
    public String getTYPE(){
        return TYPE;
    }

    @Override
    public String getUNIT_OF_MEASURE() {
        return UNIT_OF_MEASURE;
    }
}
