package model;

import java.sql.Date;

public class PillForm extends Medicine {
    private final static String TYPE = "PillForm";
    private final static String UNIT_OF_MEASURE = "Pill";

    public PillForm() {
    }

    public PillForm(int id, String name, double price, Date expiryDate, String discription) {
        super(id, name, price, expiryDate, discription);
    }

    public PillForm(String name, double price, Date expiryDate, String discription) {
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
