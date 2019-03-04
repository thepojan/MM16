package com.example.parmila.milkmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MilkManager.db";
    private static final String C_TABLE_NAME="Customer";
    private static final String COLUMN_C_ID="c_id";
    private static final String COLUMN_C_FNAME="c_fname";
    private static final String COLUMN_C_LNAME="c_lname";
    private static final String COLUMN_C_PHONE="c_phone";
    private static final String COLUMN_C_PIN="c_pin";
    private static final String COLUMN_C_EMAIL="c_email";
    private static final String COLUMN_C_PASS="c_pass";
    private static final String COLUMN_C_ADDR="c_address";

    //Customer table creation
    private static final String C_TABLE_CREATE="CREATE TABLE "+C_TABLE_NAME+"("+COLUMN_C_ID+" text primary key not null ,"
            +COLUMN_C_FNAME+" text not null,"
            +COLUMN_C_LNAME+" text not null,"
            +COLUMN_C_PHONE+" text not null,"
            +COLUMN_C_ADDR+" text not null,"
            +COLUMN_C_PIN+" text not null,"
            +COLUMN_C_EMAIL+" text not null,"
            +COLUMN_C_PASS+" text not null)";

    //Customer table drop
    private static final String C_TABLE_DROP="DROP TABLE IF EXISTS " +C_TABLE_NAME;



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(C_TABLE_CREATE);
       // this.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(C_TABLE_DROP);
        onCreate(db);

    }

    //Record Insertion
    public void insertCustomer(Customer c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        //generating unique customer id
        String query="Select * FROM "+C_TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        String cust_id="C-"+Integer.toString(cursor.getCount());
        cursor.close();

        //inserting a record
        values.put(COLUMN_C_ID,cust_id);
        values.put(COLUMN_C_FNAME, c.getC_fname());
        values.put(COLUMN_C_LNAME, c.getC_lname());
        values.put(COLUMN_C_PHONE,c.getC_phone());
        values.put(COLUMN_C_ADDR,c.getC_address());
        values.put(COLUMN_C_PIN,c.getC_pin());
        values.put(COLUMN_C_EMAIL,c.getC_email());
        values.put(COLUMN_C_PASS,c.getC_pass());

        db.insert(C_TABLE_NAME,null,values);
        db.close();
    }
    
    

    
    
    public List<Customer> getAllUser()
    {
        String[] columns= {
                COLUMN_C_ID,
                COLUMN_C_FNAME,
                COLUMN_C_LNAME,
                COLUMN_C_PHONE,
                COLUMN_C_ADDR,
                COLUMN_C_PIN,
                COLUMN_C_EMAIL,
                COLUMN_C_PASS};
        String sortOrder=COLUMN_C_ID+" ASC";
        List<Customer> custList=new ArrayList<Customer>();
        
        SQLiteDatabase db=this.getReadableDatabase();
        
        Cursor cursor=db.query(C_TABLE_NAME,columns,null,null,null,null,sortOrder);
        
        if(cursor.moveToFirst())
        {
            do{
                Customer c=new Customer();
                c.setC_id(cursor.getString(cursor.getColumnIndex(COLUMN_C_ID)));
                c.setC_fname(cursor.getString(cursor.getColumnIndex(COLUMN_C_FNAME)));
                c.setC_lname(cursor.getString(cursor.getColumnIndex(COLUMN_C_LNAME)));
                c.setC_phone(cursor.getString(cursor.getColumnIndex(COLUMN_C_PHONE)));
                c.setC_address(cursor.getString(cursor.getColumnIndex(COLUMN_C_ADDR)));
                c.setC_pin(cursor.getString(cursor.getColumnIndex(COLUMN_C_PIN)));
                c.setC_email(cursor.getString(cursor.getColumnIndex(COLUMN_C_EMAIL)));
                c.setC_pass(cursor.getString(cursor.getColumnIndex(COLUMN_C_PASS)));

                custList.add(c);
            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return custList;
    }

    public void updateCust(Customer c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_C_FNAME, c.getC_fname());
        values.put(COLUMN_C_LNAME, c.getC_lname());
        values.put(COLUMN_C_PHONE,c.getC_phone());
        values.put(COLUMN_C_ADDR,c.getC_address());
        values.put(COLUMN_C_PIN,c.getC_pin());
        values.put(COLUMN_C_EMAIL,c.getC_email());
        values.put(COLUMN_C_PASS,c.getC_pass());

        db.update(C_TABLE_NAME,values,COLUMN_C_ID +" = ?",
                new String[]{String.valueOf(c.getC_id())});
        db.close();
    }

    public void deleteUser(Customer c)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete(C_TABLE_NAME,COLUMN_C_ID +" = ?",
                new String[]{String.valueOf(c.getC_id())});
        db.close();
    }


    public boolean checkCust(String email)
    {

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+COLUMN_C_EMAIL+","+
                COLUMN_C_PASS+" FROM "+C_TABLE_NAME+" WHERE "+COLUMN_C_EMAIL+" = '"+email+"'",null);
        int cursorCount=cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount==0)
        {
            return true;
        }
        return false;
    }

    public boolean checkCust(String email, String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+COLUMN_C_EMAIL+","+
                COLUMN_C_PASS+" FROM "+C_TABLE_NAME+" WHERE "+COLUMN_C_EMAIL+" = '"+email+"'"+
                " AND "+COLUMN_C_PASS+"='"+password+"'",null);
        int cursorCount=cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount==1)
        {
            return true;
        }
        return false;
    }
}


