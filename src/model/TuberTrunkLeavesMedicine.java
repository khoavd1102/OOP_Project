package model;

import java.sql.Date;

public class TuberTrunkLeavesMedicine extends Medicine{
    private final static String UNIT_OF_MEASURE = "Kilogram";

    public TuberTrunkLeavesMedicine() {
    }

    public TuberTrunkLeavesMedicine(int id, String name, double price, Date expiryDate, String discription) {
        super(id, name, price, expiryDate, discription);
    }

    public TuberTrunkLeavesMedicine(String name, double price, Date expiryDate, String discription) {
        super(name, price, expiryDate, discription);
    }

    @Override
    public String getTYPE(){
        return null;
    }

    @Override
    public String getUNIT_OF_MEASURE() {
        return UNIT_OF_MEASURE;
    }
}
