package com.example.parmila.milkmanager.Nav_Activity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.modules.Bill;
import com.example.parmila.milkmanager.modules.Order;
import com.example.parmila.milkmanager.modules.View_Order;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Order_Now extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener sDateSetListener,eDateSetListener;
    public static final String DATE_FORMAT="d/M/yyyy";

    DatabaseHelper helper=new DatabaseHelper(this);
    RadioGroup type;
    RadioButton Cow,Buffalo;
    EditText quantity;
    Button cal_cost, Submit;
    TextView Total_Cost, startDate,endDate,perDayCost;

    Catalog c= new Catalog();
    String cust_email=c.email;
    public static String s_email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);

        Intent i=getIntent();
        s_email=i.getStringExtra("S_Email");
        String TAG="Order_Now";
        Log.d(TAG,"Seller Email"+s_email);

        Log.d(TAG,"Customer Email"+cust_email);
        initViews();

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(
                        Order_Now.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        sDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMinDate(new Date().getTime() +24*60*60*1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        Order_Now.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        eDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMinDate(new Date().getTime() +24*60*60*1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        sDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                startDate.setText(date);
            }
        };

        eDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                endDate.setText(date);
            }
        };

        cal_cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String per_cost=Integer.toString(milkCost());
                String total_cost=Integer.toString(total_mcost());
                Total_Cost.setText("\u20B9"+total_cost);
                perDayCost.setText("\u20B9"+per_cost);
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid())
                {
                    saveToOrder();
                }
            }
        });
    }

    private boolean isValid() {
        boolean valid=true;
        if(!(Cow.isChecked()) && !(Buffalo.isChecked()))
        {
            Toast.makeText(this,"Please select type of milk!",Toast.LENGTH_SHORT).show();
            valid=false;
        }

        if(quantity.getText().toString().isEmpty())
        {
            quantity.setError("Please enter quantity!");
            valid=false;
        }

        if(startDate.getText().toString().isEmpty())
        {
            startDate.setError("Please enter start date!");
            valid=false;
        }

        if(endDate.getText().toString().isEmpty())
        {
            endDate.setError("Please enter end date!");
            valid=false;
        }


        if(quantity.getText().toString().isEmpty())
        {
            quantity.setError("Please enter quantity!");
            valid=false;
        }

        if(Total_Cost.getText().toString().isEmpty())
        {
            Total_Cost.setError("Please click on calculate cost button!");
            valid=false;
        }

        if(perDayCost.getText().toString().isEmpty())
        {
            perDayCost.setError("Please click on calculate cost button!");
            valid=false;
        }
        return valid;
    }

    private int milkCost()
    {
        int mQuant=Integer.parseInt(quantity.getText().toString());
        int mID=type.getCheckedRadioButtonId();
        int cost=0;
        if(mID==R.id.cow)
        {
            cost=40;
        }
        else if(mID==R.id.buffalo)
        {
            cost=45;
        }
        else
        {
            Toast.makeText(this,"please choose type of milk",Toast.LENGTH_SHORT).show();
        }
        return cost*mQuant;
    }
    public int total_mcost()
    {
        int p_cost=milkCost();
        int no_of_days=(int)Days();
        return p_cost*no_of_days;
    }



    public void initViews()
    {
        type=findViewById(R.id.m_type);
        Cow=findViewById(R.id.cow);
        Buffalo=findViewById(R.id.buffalo);
        quantity=findViewById(R.id.m_qtty);
        cal_cost=findViewById(R.id.calc_cost);
        Submit=findViewById(R.id.submit);
        Total_Cost=findViewById(R.id.final_cost);
        perDayCost=findViewById(R.id.per_day_cost);
        startDate=findViewById(R.id.start);
        endDate=findViewById(R.id.end);

    }


    public void saveToOrder()
    {

        int mID=type.getCheckedRadioButtonId();
        String mType=" ";
        if(mID==R.id.cow)
        {
            mType="Cow";
        }
        else if(mID==R.id.buffalo)
        {
            mType="Buffalo";
        }
        String TAG="Order_Now";

        Log.d(TAG,"get Seller Email"+s_email);

        Log.d(TAG,"get Customer Email"+cust_email);

        //get current customer id and seller id

        String current_c_name=helper.getCustName(cust_email);
        String current_c_addr=helper.getCustAddr(cust_email);

       // String selected_s_id="S-0";
        String curr_s_name=helper.getSellName(s_email);

       //SQLiteDatabase db=helper.getWritableDatabase();
        Order o=new Order();
        o.setO_cname(current_c_name);
        o.setO_date(getDateTime());
        o.setO_caddr(current_c_addr);
        o.setO_sname(curr_s_name);
        o.setO_type(mType);
        o.setO_quantity(Integer.parseInt(quantity.getText().toString().trim()));
        o.setO_start(startDate.getText().toString().trim());
        o.setO_end(endDate.getText().toString().trim());
        o.setO_days((int)Days());
        o.setO_pcost(milkCost());
        o.setO_fcost(total_mcost());
        helper.insertOrder(o);
        Toast.makeText(this,"Succesfully Placed Your Order",Toast.LENGTH_SHORT).show();


        Bill b=new Bill();
        b.setB_days((int)Days());
        b.setB_start(startDate.getText().toString().trim());
        b.setB_end(endDate.getText().toString().trim());
        b.setB_type(mType);
        b.setB_qtty(Integer.parseInt(quantity.getText().toString().trim()));
        b.setB_sname(curr_s_name);
        b.setB_cname(current_c_name);
        b.setB_fcost(total_mcost());
        helper.insertBill(b);


        View_Order v=new View_Order();
        v.setV_date(getDateTime());
        v.setV_type(mType);
        v.setV_cname(current_c_name);
        v.setV_caddr(current_c_addr);
        v.setV_sname(curr_s_name);
        v.setV_qtty(Integer.parseInt(quantity.getText().toString().trim()));
        v.setV_start(startDate.getText().toString().trim());
        v.setV_end(endDate.getText().toString().trim());
        v.setV_fcost(total_mcost());
        helper.insertReport(v);
    }

    public String getDateTime()
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        Date date=new Date();
        return dateFormat.format(date);
    }

    public long Days()
    {

        long noOfDays=0;
        SimpleDateFormat dateFormat=new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        String start=startDate.getText().toString();
        String end=endDate.getText().toString();
        Date s,e;
        try {
            s = dateFormat.parse(start);
            e=dateFormat.parse(end);
           noOfDays=getUnitBetweenDates(s,e,TimeUnit.DAYS);
        }
        catch(ParseException ex)
        {
            Log.v("Exception",ex.getLocalizedMessage());
        }
        return (noOfDays+1);
    }

    private long getUnitBetweenDates(Date s, Date e, TimeUnit days) {
        long timeDiff=e.getTime()-s.getTime();
        return days.convert(timeDiff,TimeUnit.MILLISECONDS);
    }


}
