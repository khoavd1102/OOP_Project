package model;

import java.sql.Date;

public class TrunkMedicine extends TuberTrunkLeavesMedicine{
    private final static String TYPE = "Trunk";

    public TrunkMedicine() {
    }

    public TrunkMedicine(int id, String name, double price, Date expiryDate, String discription) {
        super(id, name, price, expiryDate, discription);
    }

    public TrunkMedicine(String name, double price, Date expiryDate, String discription) {
        super(name, price, expiryDate, discription);
    }

    @Override
    public String getTYPE(){
        return TYPE;
    }
}
