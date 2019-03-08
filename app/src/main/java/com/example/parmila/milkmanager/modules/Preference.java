package com.example.parmila.milkmanager.modules;

public class Preference {

    String p_id, type,pc_id;
    int quantity;

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getPc_id() {
        return pc_id;
    }

    public void setPc_id(String pc_id) {
        this.pc_id = pc_id;
    }

    public void setP_Quantity(int quantity) {
        this.quantity = quantity;
    }

    public int getP_Quantity() {
        return quantity;
    }

    public void setP_Type(String type) {
        this.type = type;
    }

    public String getP_Type() {
        return type;
    }
}
