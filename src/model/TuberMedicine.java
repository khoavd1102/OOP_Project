package model;

import java.sql.Date;

public class TuberMedicine extends TuberTrunkLeavesMedicine{
    private final static String TYPE = "Tuber";

    public TuberMedicine() {
    }

    public TuberMedicine(int id, String name, double price, Date expiryDate, String discription) {
        super(id, name, price, expiryDate, discription);
    }

    public TuberMedicine(String name, double price, Date expiryDate, String discription) {
        super(name, price, expiryDate, discription);
    }

    @Override
    public String getTYPE(){
        return TYPE;
    }
}
