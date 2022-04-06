package model;

import java.sql.Date;

public class LeavesMedicine extends TuberTrunkLeavesMedicine{
    private final static String TYPE = "Leaves";

    public LeavesMedicine() {
    }

    public LeavesMedicine(int id, String name, double price, Date expiryDate, String discription) {
        super(id, name, price, expiryDate, discription);
    }

    public LeavesMedicine(String name, double price, Date expiryDate, String discription) {
        super(name, price, expiryDate, discription);
    }

    @Override
    public String getTYPE() {
        return TYPE;
    }
}
