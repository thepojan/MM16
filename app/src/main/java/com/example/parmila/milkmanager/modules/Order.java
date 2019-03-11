package com.example.parmila.milkmanager.modules;

import java.util.Date;

public class Order {
    String o_id, o_cname,o_sname,o_caddr,o_type;
    int o_quantity, o_fcost,o_pcost, o_days;
    String o_date, o_start, o_end;

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getO_id() {
        return o_id;
    }


    public String getO_cname() {
        return o_cname;
    }

    public void setO_cname(String o_cname) {
        this.o_cname = o_cname;
    }

    public void setO_sname(String o_sname) {
        this.o_sname = o_sname;
    }

    public String getO_sname() {
        return o_sname;
    }

    public String getO_caddr() {
        return o_caddr;
    }

    public void setO_caddr(String o_caddr) {
        this.o_caddr = o_caddr;
    }

    public String getO_type() {
        return o_type;
    }

    public void setO_type(String o_type) {
        this.o_type = o_type;
    }

    public void setO_quantity(int o_quantity) {
        this.o_quantity = o_quantity;
    }

    public int getO_quantity() {
        return o_quantity;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public void setO_start(String o_start) {
        this.o_start = o_start;
    }

    public String getO_start() {
        return o_start;
    }

    public void setO_end(String o_end) {
        this.o_end = o_end;
    }

    public String getO_end() {
        return o_end;
    }

    public void setO_pcost(int o_cost) {
        this.o_pcost = o_cost;
    }

    public int getO_pcost() {
        return o_pcost;
    }

    public void setO_fcost(int o_fcost) {
        this.o_fcost = o_fcost;
    }

    public int getO_fcost() {
        return o_fcost;
    }

    public void setO_days(int o_days) {
        this.o_days = o_days;
    }

    public int getO_days() {
        return o_days;
    }
}
