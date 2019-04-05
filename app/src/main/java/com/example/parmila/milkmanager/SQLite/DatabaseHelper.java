package com.example.parmila.milkmanager.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.Nav_Activity.Order_Detail;
import com.example.parmila.milkmanager.modules.Bill;
import com.example.parmila.milkmanager.modules.Customer;
import com.example.parmila.milkmanager.modules.Order;
import com.example.parmila.milkmanager.modules.View_Order;
import com.example.parmila.milkmanager.modules.Seller;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MilkManager.db";

    //Customer Table
    private static final String C_TABLE_NAME = "Customer";
    private static final String COLUMN_C_ID = "c_id";
    private static final String COLUMN_C_FNAME = "c_fname";
    private static final String COLUMN_C_LNAME = "c_lname";
    private static final String COLUMN_C_PHONE = "c_phone";
    private static final String COLUMN_C_PIN = "c_pin";
    private static final String COLUMN_C_EMAIL = "c_email";
    private static final String COLUMN_C_PASS = "c_pass";
    private static final String COLUMN_C_ADDR = "c_address";

    //Seller Table
    private static final String S_TABLE_NAME = "Seller";
    private static final String COLUMN_S_ID = "s_id";
    private static final String COLUMN_S_FNAME = "s_fname";
    private static final String COLUMN_S_LNAME = "s_lname";
    private static final String COLUMN_S_PHONE = "s_phone";
    private static final String COLUMN_S_PIN = "s_pin";
    private static final String COLUMN_S_EMAIL = "s_email";
    private static final String COLUMN_S_PASS = "s_pass";


    //Milk Table

    private static final String M_TABLE_NAME = "Milk";
    private static final String COLUMN_M_TYPE = "m_type";
    private static final String COLUMN_M_PRICE = "m_price";

    //Order table
    private static final String O_TABLE_NAME = "MOrder";
    private static final String COLUMN_O_ID = "o_id";
    private static final String COLUMN_O_DATE = "o_date";
    private static final String COLUMN_O_CNAME = "o_c_name";
    private static final String COLUMN_O_CADDR = "o_addr";
    private static final String COLUMN_O_SNAME = "o_s_name";
    private static final String COLUMN_O_SDATE = "o_sdate";
    private static final String COLUMN_O_EDATE = "o_edate";
    private static final String COLUMN_O_DAYS = "o_days";
    private static final String COLUMN_O_TYPE = "o_type";
    private static final String COLUMN_O_QUANTITY = "o_quantity";
    private static final String COLUMN_O_COST_PER_DAY = "o_per_day_cost";
    private static final String COLUMN_O_FINAL_COST = "o_final_cost";

    //Bill table
    private static String B_TABLE_NAME = "Bill";
    private static final String COLUMN_B_ID = "b_id";
    private static final String COLUMN_B_SNAME = "b_s_name";
    private static final String COLUMN_B_CNAME = "b_c_name";
    private static final String COLUMN_B_DAYS = "b_days";
    private static final String COLUMN_B_SDATE = "b_sdate";
    private static final String COLUMN_B_EDATE = "b_edate";
    private static final String COLUMN_B_TYPE = "b_type";
    private static final String COLUMN_B_QUANTITY = "b_quantity";
    private static final String COLUMN_B_FINAL_COST = "b_final_cost";

    //View Order table
    private static String V_TABLE_NAME = "View_Order";
    private static final String COLUMN_V_DATE = "v_date";
    private static final String COLUMN_V_SNAME = "v_sname";
    private static final String COLUMN_V_CNAME = "v_cname";
    private static final String COLUMN_V_CADDR = "v_caddr";
    private static final String COLUMN_V_TYPE = "v_type";
    private static final String COLUMN_V_QUANTITY = "v_quantity";
    private static final String COLUMN_V_SDATE = "v_sdate";
    private static final String COLUMN_V_EDATE = "v_edate";
    private static final String COLUMN_V_FINAL_COST = "v_final_cost";


    // MILK TABLE Creation
    private static final String M_TABLE_CREATE = " CREATE TABLE " + M_TABLE_NAME + "(" + COLUMN_M_TYPE + " text primary key not null , "
            + COLUMN_M_PRICE + " integer not null)";


    //Customer table creation
    private static final String C_TABLE_CREATE = "CREATE TABLE " + C_TABLE_NAME + "(" + COLUMN_C_ID + " text primary key not null ,"
            + COLUMN_C_FNAME + " text not null,"
            + COLUMN_C_LNAME + " text not null,"
            + COLUMN_C_PHONE + " text not null,"
            + COLUMN_C_ADDR + " text not null,"
            + COLUMN_C_PIN + " text not null,"
            + COLUMN_C_EMAIL + " text not null,"
            + COLUMN_C_PASS + " text not null)";

    //Seller table creation
    private static final String S_TABLE_CREATE = " CREATE TABLE " + S_TABLE_NAME + "(" + COLUMN_S_ID + " text primary key not null ,"
            + COLUMN_S_FNAME + " text not null,"
            + COLUMN_S_LNAME + " text not null,"
            + COLUMN_S_PHONE + " text not null,"
            + COLUMN_S_PIN + " text not null,"
            + COLUMN_S_EMAIL + " text not null,"
            + COLUMN_S_PASS + " text not null)";


    //Order table create
    private static final String O_TABLE_CREATE = " CREATE TABLE " + O_TABLE_NAME + "(" + COLUMN_O_ID + " text not null,"
            + COLUMN_O_DATE + " text not null,"
            + COLUMN_O_CNAME + " text not null,"
            + COLUMN_O_CADDR + " text not null,"
            + COLUMN_O_SNAME + " text not null,"
            + COLUMN_O_TYPE + " text not null,"
            + COLUMN_O_QUANTITY + " integer not null,"
            + COLUMN_O_SDATE + " text not null,"
            + COLUMN_O_EDATE + " text not null,"
            + COLUMN_O_DAYS + " integer not null,"
            + COLUMN_O_COST_PER_DAY + " integer not null,"
            + COLUMN_O_FINAL_COST + " integer not null)";

    //Bill table create
    private static final String B_TABLE_CREATE = " CREATE TABLE " + B_TABLE_NAME + "(" + COLUMN_B_ID + " text not null,"
            + COLUMN_B_SNAME + " text not null,"
            + COLUMN_B_CNAME + " text not null,"
            + COLUMN_B_TYPE + " text not null,"
            + COLUMN_B_QUANTITY + " integer not null,"
            + COLUMN_B_SDATE + " text not null,"
            + COLUMN_B_EDATE + " text not null,"
            + COLUMN_B_DAYS + " integer not null,"
            + COLUMN_B_FINAL_COST + " integer not null)";

    //View table create
    private static final String V_TABLE_CREATE = " CREATE TABLE " + V_TABLE_NAME + "("
            + COLUMN_V_TYPE + " text not null,"
            + COLUMN_V_SNAME + " text not null,"
            + COLUMN_V_CNAME + " text not null,"
            + COLUMN_V_CADDR + " text not null,"
            + COLUMN_V_QUANTITY + " integer not null,"
            + COLUMN_V_SDATE + " text not null,"
            + COLUMN_V_EDATE + " text not null,"
            + COLUMN_V_DATE + " text not null,"
            + COLUMN_V_FINAL_COST + " integer not null)";


    //Customer table drop
    private static final String C_TABLE_DROP = "DROP TABLE IF EXISTS " + C_TABLE_NAME;

    //Seller table drop
    private static final String S_TABLE_DROP = "DROP TABLE IF EXISTS " + S_TABLE_NAME;

    //Milk table drop
    private static final String M_TABLE_DROP = "DROP TABLE IF EXISTS " + M_TABLE_NAME;

    //Order table drop
    private static final String O_TABLE_DROP = "DROP TABLE IF EXISTS " + O_TABLE_NAME;

    //Bill table drop
    private static final String B_TABLE_DROP = "DROP TABLE IF EXISTS " + B_TABLE_NAME;

    //View table drop
    private static final String V_TABLE_DROP = "DROP TABLE IF EXISTS " + V_TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(C_TABLE_CREATE);
        db.execSQL(S_TABLE_CREATE);
        db.execSQL(M_TABLE_CREATE);
        db.execSQL(O_TABLE_CREATE);
        db.execSQL(B_TABLE_CREATE);
        db.execSQL(V_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(C_TABLE_DROP);
        db.execSQL(S_TABLE_DROP);
        db.execSQL(M_TABLE_DROP);
        db.execSQL(O_TABLE_DROP);
        db.execSQL(B_TABLE_DROP);
        db.execSQL(V_TABLE_DROP);
        onCreate(db);

    }


    public void insertMilk() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COLUMN_M_TYPE, "Cow");
        val.put(COLUMN_M_PRICE, 40);
        db.insert(M_TABLE_NAME, null, val);
        val.put(COLUMN_M_TYPE, "Buffalo");
        val.put(COLUMN_M_PRICE, 45);
        db.insert(M_TABLE_NAME, null, val);
        db.close();

    }

    // Customer Record Insertion
    public void insertCustomer(Customer c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //generating unique customer id
        String query = "Select * FROM " + C_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        String cust_id = "C-" + Integer.toString(cursor.getCount());
        cursor.close();

        //inserting a record
        values.put(COLUMN_C_ID, cust_id);
        values.put(COLUMN_C_FNAME, c.getC_fname());
        values.put(COLUMN_C_LNAME, c.getC_lname());
        values.put(COLUMN_C_PHONE, c.getC_phone());
        values.put(COLUMN_C_ADDR, c.getC_address());
        values.put(COLUMN_C_PIN, c.getC_pin());
        values.put(COLUMN_C_EMAIL, c.getC_email());
        values.put(COLUMN_C_PASS, c.getC_pass());

        db.insert(C_TABLE_NAME, null, values);
        db.close();
    }

    //Seller Record Insertion
    public void insertSeller(Seller s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //generating unique customer id
        String query = "Select * FROM " + S_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        String seller_id = "S-" + Integer.toString(cursor.getCount());
        cursor.close();

        //inserting a record
        values.put(COLUMN_S_ID, seller_id);
        values.put(COLUMN_S_FNAME, s.getS_fname());
        values.put(COLUMN_S_LNAME, s.getS_lname());
        values.put(COLUMN_S_PHONE, s.getS_phone());
        values.put(COLUMN_S_PIN, s.getS_pin());
        values.put(COLUMN_S_EMAIL, s.getS_email());
        values.put(COLUMN_S_PASS, s.getS_pass());

        db.insert(S_TABLE_NAME, null, values);
        db.close();
    }

    //insert Order record
    public void insertOrder(Order o) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //generating unique customer id
        String query = "Select * FROM " + O_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        String o_id = "O-" + Integer.toString(cursor.getCount());
        cursor.close();

        //inserting a record
        values.put(COLUMN_O_ID, o_id);
        values.put(COLUMN_O_DATE, o.getO_date());
        values.put(COLUMN_O_CNAME, o.getO_cname());
        values.put(COLUMN_O_CADDR, o.getO_caddr());
        values.put(COLUMN_O_SNAME, o.getO_sname());
        values.put(COLUMN_O_TYPE, o.getO_type());
        values.put(COLUMN_O_QUANTITY, o.getO_quantity());
        values.put(COLUMN_O_SDATE, o.getO_start());
        values.put(COLUMN_O_EDATE, o.getO_end());
        values.put(COLUMN_O_DAYS, o.getO_days());
        values.put(COLUMN_O_COST_PER_DAY, o.getO_pcost());
        values.put(COLUMN_O_FINAL_COST, o.getO_fcost());
        db.insert(O_TABLE_NAME, null, values);
        db.close();
    }


    //insert bill record
    public void insertBill(Bill b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //generating unique customer id
        String query = "Select * FROM " + B_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        String b_id = "B-" + Integer.toString(cursor.getCount());
        cursor.close();

        //inserting a record
        values.put(COLUMN_B_ID, b_id);
        values.put(COLUMN_B_SNAME, b.getB_sname());
        values.put(COLUMN_B_CNAME, b.getB_cname());
        values.put(COLUMN_B_TYPE, b.getB_type());
        values.put(COLUMN_B_QUANTITY, b.getB_qtty());
        values.put(COLUMN_B_SDATE, b.getB_start());
        values.put(COLUMN_B_EDATE, b.getB_end());
        values.put(COLUMN_B_DAYS, b.getB_days());
        values.put(COLUMN_B_FINAL_COST, b.getB_fcost());
        db.insert(B_TABLE_NAME, null, values);
        db.close();
    }


    //insert View Order list
    public void insertReport(View_Order r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //generating unique customer id
        String query = "Select * FROM " + V_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        cursor.close();

        //inserting a record
        values.put(COLUMN_V_TYPE, r.getV_type());
        values.put(COLUMN_V_CNAME, r.getV_cname());
        values.put(COLUMN_V_CADDR, r.getV_caddr());
        values.put(COLUMN_V_SNAME, r.getV_sname());
        values.put(COLUMN_V_QUANTITY, r.getV_qtty());
        values.put(COLUMN_V_SDATE, r.getV_start());
        values.put(COLUMN_V_EDATE, r.getV_end());
        values.put(COLUMN_V_DATE, r.getV_date());
        values.put(COLUMN_V_FINAL_COST, r.getV_fcost());
        db.insert(V_TABLE_NAME, null, values);
        db.close();
    }


    public List<Customer> getAllCust() {
        String[] columns = {
                COLUMN_C_ID,
                COLUMN_C_FNAME,
                COLUMN_C_LNAME,
                COLUMN_C_PHONE,
                COLUMN_C_ADDR,
                COLUMN_C_PIN,
                COLUMN_C_EMAIL,
                COLUMN_C_PASS};
        String sortOrder = COLUMN_C_ID + " ASC";
        List<Customer> custList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(C_TABLE_NAME, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Customer c = new Customer();
                c.setC_id(cursor.getString(cursor.getColumnIndex(COLUMN_C_ID)));
                c.setC_fname(cursor.getString(cursor.getColumnIndex(COLUMN_C_FNAME)));
                c.setC_lname(cursor.getString(cursor.getColumnIndex(COLUMN_C_LNAME)));
                c.setC_phone(cursor.getString(cursor.getColumnIndex(COLUMN_C_PHONE)));
                c.setC_address(cursor.getString(cursor.getColumnIndex(COLUMN_C_ADDR)));
                c.setC_pin(cursor.getString(cursor.getColumnIndex(COLUMN_C_PIN)));
                c.setC_email(cursor.getString(cursor.getColumnIndex(COLUMN_C_EMAIL)));
                c.setC_pass(cursor.getString(cursor.getColumnIndex(COLUMN_C_PASS)));

                custList.add(c);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return custList;
    }


 /*   public String getCustID(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String where="Select * from "+C_TABLE_NAME+" where "+COLUMN_C_EMAIL+ " like '%"+email+"%'";
        Cursor c=db.rawQuery(where,null);
        if(c.getCount()>0 && c.moveToNext())
            return c.getString(0);
        else
            return "0";
    }*/

    public String getCustName(String email) {
        String TAG = "DB";
        Log.d(TAG, "Cust Email" + email);


        SQLiteDatabase db = this.getReadableDatabase();
        String where = "Select * from " + C_TABLE_NAME + " where " + COLUMN_C_EMAIL + " like '%" + email + "%'";
        Cursor c = db.rawQuery(where, null);
        if (c.getCount() > 0 && c.moveToNext())
            return (c.getString(1) + " " + c.getString(2));
        else
            return "0";
    }

    public String getCustAddr(String email) {
        String TAG = "DB";
        Log.d(TAG, "Cust Email" + email);

        SQLiteDatabase db = this.getReadableDatabase();
        String where = "Select * from " + C_TABLE_NAME + " where " + COLUMN_C_EMAIL + " like '%" + email + "%'";
        Cursor c = db.rawQuery(where, null);
        if (c.getCount() > 0 && c.moveToNext())
            return (c.getString(4));
        else
            return "0";
    }

    public String getCustPhone(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String where = "Select * from " + C_TABLE_NAME + " where " + COLUMN_C_EMAIL + " like '%" + email + "%'";
        Cursor c = db.rawQuery(where, null);
        if (c.getCount() > 0 && c.moveToNext())
            return (c.getString(3));
        else
            return "0";
    }

    public String getCustZip(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String where = "Select * from " + C_TABLE_NAME + " where " + COLUMN_C_EMAIL + " like '%" + email + "%'";
        Cursor c = db.rawQuery(where, null);
        if (c.getCount() > 0 && c.moveToNext())
            return (c.getString(5));
        else
            return "0";
    }

    //get all seller
    public List<Seller> getAllSeller() {
        String[] columns = {
                COLUMN_S_ID,
                COLUMN_S_FNAME,
                COLUMN_S_LNAME,
                COLUMN_S_PHONE,
                COLUMN_S_PIN,
                COLUMN_S_EMAIL,
                COLUMN_S_PASS};
        String sortOrder = COLUMN_S_ID + " ASC";
        List<Seller> sellerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(S_TABLE_NAME, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Seller s = new Seller();
                s.setS_id(cursor.getString(cursor.getColumnIndex(COLUMN_S_ID)));
                s.setS_fname(cursor.getString(cursor.getColumnIndex(COLUMN_S_FNAME)));
                s.setS_lname(cursor.getString(cursor.getColumnIndex(COLUMN_S_LNAME)));
                s.setS_phone(cursor.getString(cursor.getColumnIndex(COLUMN_S_PHONE)));
                s.setS_pin(cursor.getString(cursor.getColumnIndex(COLUMN_S_PIN)));
                s.setS_email(cursor.getString(cursor.getColumnIndex(COLUMN_S_EMAIL)));
                s.setS_pass(cursor.getString(cursor.getColumnIndex(COLUMN_S_PASS)));

                sellerList.add(s);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return sellerList;
    }


  /*  public List<Bill> getAllBills()
    {
        String[] columns= {
                COLUMN_B_ID,
                COLUMN_B_SNAME,
                COLUMN_B_TYPE,
                COLUMN_B_QUANTITY,
                COLUMN_B_SDATE,
                COLUMN_B_EDATE,
                COLUMN_B_DAYS,
                COLUMN_B_FINAL_COST
        };

        String sortOrder=COLUMN_B_ID+" DESC";
        List<Bill> billList= new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(B_TABLE_NAME,columns,null,null,null,null,sortOrder);

        if(cursor.moveToFirst())
        {
            do{
                Bill b=new Bill();
                b.setB_sname(cursor.getString(cursor.getColumnIndex(COLUMN_B_ID)));
                b.setB_sname(cursor.getString(cursor.getColumnIndex(COLUMN_B_SNAME)));
                b.setB_sname(cursor.getString(cursor.getColumnIndex(COLUMN_B_TYPE)));
                b.setB_qtty(cursor.getInt(cursor.getColumnIndex(COLUMN_B_QUANTITY)));
                b.setB_start(cursor.getString(cursor.getColumnIndex(COLUMN_B_SDATE)));
                b.setB_end(cursor.getString(cursor.getColumnIndex(COLUMN_B_EDATE)));
                b.setB_days(cursor.getInt(cursor.getColumnIndex(COLUMN_B_DAYS)));
                b.setB_fcost(cursor.getInt(cursor.getColumnIndex(COLUMN_B_FINAL_COST)));
                billList.add(b);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return billList;
    }*/


    public List<Bill> getAllBills(String cname, String sname) {

        Log.d("DB:", cname + " : " + sname);

        String[] columns = {
                COLUMN_B_ID,
                COLUMN_B_SNAME,
                COLUMN_B_CNAME,
                COLUMN_B_TYPE,
                COLUMN_B_QUANTITY,
                COLUMN_B_SDATE,
                COLUMN_B_EDATE,
                COLUMN_B_DAYS,
                COLUMN_B_FINAL_COST
        };

        String sortOrder = COLUMN_B_ID + " DESC";
        List<Bill> billList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(B_TABLE_NAME, columns, COLUMN_B_CNAME + "='" + cname + "' AND " +
                COLUMN_B_SNAME + "='" + sname + "'", null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Bill b = new Bill();
                b.setB_id(cursor.getString(cursor.getColumnIndex(COLUMN_B_ID)));
                b.setB_sname(cursor.getString(cursor.getColumnIndex(COLUMN_B_SNAME)));
                b.setB_cname(cursor.getString(cursor.getColumnIndex(COLUMN_B_CNAME)));
                b.setB_sname(cursor.getString(cursor.getColumnIndex(COLUMN_B_TYPE)));
                b.setB_qtty(cursor.getInt(cursor.getColumnIndex(COLUMN_B_QUANTITY)));
                b.setB_start(cursor.getString(cursor.getColumnIndex(COLUMN_B_SDATE)));
                b.setB_end(cursor.getString(cursor.getColumnIndex(COLUMN_B_EDATE)));
                b.setB_days(cursor.getInt(cursor.getColumnIndex(COLUMN_B_DAYS)));
                b.setB_fcost(cursor.getInt(cursor.getColumnIndex(COLUMN_B_FINAL_COST)));
                billList.add(b);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return billList;
    }

    public List<Bill> getAllBills(String sname) {

        // Log.d("DB:",cname+" : "+sname);

        String[] columns = {
                COLUMN_B_ID,
                COLUMN_B_SNAME,
                COLUMN_B_CNAME,
                COLUMN_B_TYPE,
                COLUMN_B_QUANTITY,
                COLUMN_B_SDATE,
                COLUMN_B_EDATE,
                COLUMN_B_DAYS,
                COLUMN_B_FINAL_COST
        };

        String sortOrder = COLUMN_B_ID + " DESC";
        List<Bill> billList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(B_TABLE_NAME, columns, COLUMN_B_SNAME + "='" + sname + "'", null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Bill b = new Bill();
                b.setB_id(cursor.getString(cursor.getColumnIndex(COLUMN_B_ID)));
                b.setB_sname(cursor.getString(cursor.getColumnIndex(COLUMN_B_SNAME)));
                b.setB_cname(cursor.getString(cursor.getColumnIndex(COLUMN_B_CNAME)));
                b.setB_sname(cursor.getString(cursor.getColumnIndex(COLUMN_B_TYPE)));
                b.setB_qtty(cursor.getInt(cursor.getColumnIndex(COLUMN_B_QUANTITY)));
                b.setB_start(cursor.getString(cursor.getColumnIndex(COLUMN_B_SDATE)));
                b.setB_end(cursor.getString(cursor.getColumnIndex(COLUMN_B_EDATE)));
                b.setB_days(cursor.getInt(cursor.getColumnIndex(COLUMN_B_DAYS)));
                b.setB_fcost(cursor.getInt(cursor.getColumnIndex(COLUMN_B_FINAL_COST)));
                billList.add(b);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return billList;
    }


    public List<View_Order> getAllOrders(String cname, String sname) {

        Log.d("GetAllOrder:", cname + " : " + sname);

        String[] columns = {
                COLUMN_V_TYPE,
                COLUMN_V_CNAME,
                COLUMN_V_SNAME,
                COLUMN_V_QUANTITY,
                COLUMN_V_SDATE,
                COLUMN_V_EDATE,
                COLUMN_V_DATE,
                COLUMN_V_FINAL_COST
        };

        String sortOrder = COLUMN_V_DATE + " DESC";
        List<View_Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(V_TABLE_NAME, columns, COLUMN_V_CNAME + "='" + cname + "' AND " +
                COLUMN_V_SNAME + "='" + sname + "'", null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                View_Order v = new View_Order();

                v.setV_type(cursor.getString(cursor.getColumnIndex(COLUMN_V_TYPE)));
                v.setV_cname(cursor.getString(cursor.getColumnIndex(COLUMN_V_CNAME)));
                v.setV_sname(cursor.getString(cursor.getColumnIndex(COLUMN_V_SNAME)));
                v.setV_qtty(cursor.getInt(cursor.getColumnIndex(COLUMN_V_QUANTITY)));
                v.setV_start(cursor.getString(cursor.getColumnIndex(COLUMN_V_SDATE)));
                v.setV_end(cursor.getString(cursor.getColumnIndex(COLUMN_V_EDATE)));
                v.setV_date(cursor.getString(cursor.getColumnIndex(COLUMN_V_DATE)));
                v.setV_fcost(cursor.getInt(cursor.getColumnIndex(COLUMN_V_FINAL_COST)));
                orderList.add(v);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return orderList;
    }


    public List<View_Order> getAllOrders(String sname) {

        // Log.d("GetAllOrder:",cname+" : "+sname);

        String[] columns = {
                COLUMN_V_TYPE,
                COLUMN_V_CNAME,
                COLUMN_V_SNAME,
                COLUMN_V_QUANTITY,
                COLUMN_V_SDATE,
                COLUMN_V_EDATE,
                COLUMN_V_DATE,
                COLUMN_V_FINAL_COST
        };

        String sortOrder = COLUMN_V_DATE + " DESC";
        List<View_Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(V_TABLE_NAME, columns, COLUMN_V_SNAME + "='" + sname + "'", null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                View_Order v = new View_Order();

                v.setV_type(cursor.getString(cursor.getColumnIndex(COLUMN_V_TYPE)));
                v.setV_cname(cursor.getString(cursor.getColumnIndex(COLUMN_V_CNAME)));
                v.setV_sname(cursor.getString(cursor.getColumnIndex(COLUMN_V_SNAME)));
                v.setV_qtty(cursor.getInt(cursor.getColumnIndex(COLUMN_V_QUANTITY)));
                v.setV_start(cursor.getString(cursor.getColumnIndex(COLUMN_V_SDATE)));
                v.setV_end(cursor.getString(cursor.getColumnIndex(COLUMN_V_EDATE)));
                v.setV_date(cursor.getString(cursor.getColumnIndex(COLUMN_V_DATE)));
                v.setV_fcost(cursor.getInt(cursor.getColumnIndex(COLUMN_V_FINAL_COST)));
                orderList.add(v);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return orderList;
    }


    public String getSellName(String email) {
        String TAG = "DB";
        Log.d(TAG, "Seller Email" + email);

        SQLiteDatabase db = this.getReadableDatabase();
        String where = "Select * from " + S_TABLE_NAME + " where " + COLUMN_S_EMAIL + " like '%" + email + "%'";
        Cursor c = db.rawQuery(where, null);
        if (c.getCount() > 0 && c.moveToNext())
            return (c.getString(1) + " " + c.getString(2));
        else
            return "0";
    }

    public void updateCust(String cemail, String addr, String phone, String zip) {
        // Customer c=new Customer();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put(COLUMN_C_FNAME, c.getC_fname());
        // values.put(COLUMN_C_LNAME, c.getC_lname());
        values.put(COLUMN_C_PHONE, phone);
        values.put(COLUMN_C_ADDR, addr);
        values.put(COLUMN_C_PIN, zip);
        // values.put(COLUMN_C_EMAIL,c.getC_email());
        //  values.put(COLUMN_C_PASS,c.getC_pass());

        db.update(C_TABLE_NAME, values, COLUMN_C_EMAIL + " ='" + cemail + "'", null);
        db.close();
    }

    public void deleteCust(String cemail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(C_TABLE_NAME, COLUMN_C_EMAIL + " = '" + cemail + "'", null);
        db.close();
    }


    public int custCount(String sname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String where="Select * from "+O_TABLE_NAME+" where "+ COLUMN_O_SNAME+ " like '%"+ sname+"%'";
        Cursor c=db.rawQuery(where,null);
        return c.getCount();
    }



    public String getSellPhone(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String where = "Select * from " + S_TABLE_NAME + " where " + COLUMN_S_EMAIL + " like '%" + email + "%'";
        Cursor c = db.rawQuery(where, null);
        if (c.getCount() > 0 && c.moveToNext())
            return (c.getString(3));
        else
            return "0";
    }

    public String getSellZip(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String where = "Select * from " + S_TABLE_NAME + " where " + COLUMN_S_EMAIL + " like '%" + email + "%'";
        Cursor c = db.rawQuery(where, null);
        if (c.getCount() > 0 && c.moveToNext())
            return (c.getString(4));
        else
            return "0";
    }

    // To check if the customer already exists or not
    public boolean checkCust(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_C_EMAIL + "," +
                COLUMN_C_PASS + " FROM " + C_TABLE_NAME + " WHERE " + COLUMN_C_EMAIL + " = '" + email + "'", null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount == 0) {
            return true;
        }
        return false;
    }

    // To check if the Seller login details are correct or not
    public boolean checkCust(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_C_EMAIL + "," +
                COLUMN_C_PASS + " FROM " + C_TABLE_NAME + " WHERE " + COLUMN_C_EMAIL + " = '" + email + "'" +
                " AND " + COLUMN_C_PASS + "='" + password + "'", null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount == 1) {
            return true;
        }
        return false;
    }

    // To check if the Seller already exists or not
    public boolean checkSeller(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_S_EMAIL + " FROM " + S_TABLE_NAME + " WHERE " + COLUMN_S_EMAIL + " = '" + email + "'", null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount == 0) {
            return true;
        }
        return false;
    }

    // To check if the Seller login details are correct or not
    public boolean checkSeller(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_S_EMAIL + "," +
                COLUMN_S_PASS + " FROM " + S_TABLE_NAME + " WHERE " + COLUMN_S_EMAIL + " = '" + email + "'" +
                " AND " + COLUMN_S_PASS + "='" + password + "'", null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount == 1) {
            return true;
        }
        return false;
    }


    public Cursor getViewOrder(String date, int cost) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + V_TABLE_NAME + " WHERE " +
                COLUMN_V_DATE + "='" + date + "' AND " + COLUMN_V_FINAL_COST + "='" + cost + "'", null);
        return cursor;
    }


    public Cursor getBill(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + B_TABLE_NAME + " WHERE " +
                COLUMN_B_ID + "='" + id + "'", null);
        return cursor;
    }

}


