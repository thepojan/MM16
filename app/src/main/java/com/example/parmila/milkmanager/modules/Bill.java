package com.example.parmila.milkmanager.modules;

public class Bill {

    String b_id,b_sname,b_type,b_start,b_end, b_cname;
    int b_qtty,b_days,b_fcost;

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_cname(String b_cname) {
        this.b_cname = b_cname;
    }

    public String getB_cname() {
        return b_cname;
    }

    public void setB_qtty(int b_qtty) {
        this.b_qtty = b_qtty;
    }

    public int getB_qtty() {
        return b_qtty;
    }


    public void setB_sname(String b_sname) {
        this.b_sname = b_sname;
    }

    public String getB_sname() {
        return b_sname;
    }

    public void setB_type(String b_type) {
        this.b_type = b_type;
    }

    public String getB_type() {
        return b_type;
    }

    public void setB_start(String b_start) {
        this.b_start = b_start;
    }

    public String getB_start() {
        return b_start;
    }

    public void setB_end(String b_end) {
        this.b_end = b_end;
    }

    public String getB_end() {
        return b_end;
    }

    public void setB_days(int b_days) {
        this.b_days = b_days;
    }

    public int getB_days() {
        return b_days;
    }

    public void setB_fcost(int b_fcost) {
        this.b_fcost = b_fcost;
    }

    public int getB_fcost() {
        return b_fcost;
    }
}
