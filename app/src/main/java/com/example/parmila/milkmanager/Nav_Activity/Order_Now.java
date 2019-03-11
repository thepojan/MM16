package com.example.parmila.milkmanager.Nav_Activity;


import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.DateInterval;
import android.os.Bundle;
import android.provider.CalendarContract;
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

import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.modules.Customer;
import com.example.parmila.milkmanager.modules.Order;
import com.example.parmila.milkmanager.modules.Seller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;



public class Order_Now extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener sDateSetListener,eDateSetListener;
    Customer c=new Customer();
    Seller s=new Seller();
    DatabaseHelper helper=new DatabaseHelper(this);
    RadioGroup type;
    RadioButton Cow,Buffalo;
    EditText quantity;
    Button cal_cost, Submit;
    TextView Total_Cost, startDate,endDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);
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
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        sDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=month+"/"+dayOfMonth+"/"+year;
                startDate.setText(date);
            }
        };
        eDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=month+"/"+dayOfMonth+"/"+year;
                endDate.setText(date);
            }
        };





        cal_cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalCost=Integer.toString(milkCost());
                Total_Cost.setText((finalCost));
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveToOrder();
            }
        });
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
        startDate=findViewById(R.id.start);
        endDate=findViewById(R.id.end);
    }


    public void saveToOrder()
    {
       Order o=new Order();
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

        //get cust name
        String cname= String.valueOf(c.getC_fname()+c.getC_lname());
        String addr=String.valueOf(c.getC_address());
        String sname=String.valueOf(s.getS_fname()+c.getC_lname());
       SQLiteDatabase db=helper.getWritableDatabase();
        o.setO_cname(cname);
        o.setO_date(getDateTime());
        o.setO_caddr(addr);
        o.setO_sname(sname);
        o.setO_type(mType);
        o.setO_quantity(Integer.parseInt(quantity.getText().toString()));
        o.setO_start(startDate.getText().toString());
        o.setO_end(endDate.getText().toString());
        o.setO_days((int)Days());
        o.setO_pcost(milkCost());
        o.setO_fcost(total_mcost());
        helper.insertOrder(o);

        Toast.makeText(this,"Succesfully saved your preference",Toast.LENGTH_SHORT).show();
    }

    public String getDateTime()
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date=new Date();
        return dateFormat.format(date);
    }

    public long Days()
    {

        long noOfDays=0;
        SimpleDateFormat dateFormat=new SimpleDateFormat();
        try {
            Date start = dateFormat.parse(startDate.getText().toString());
            Date end=dateFormat.parse((endDate.getText().toString()));
            noOfDays=end.getTime()-start.getTime();
        }
        catch(ParseException ex)
        {
            Log.v("Exception",ex.getLocalizedMessage());
        }
        return noOfDays;
    }


}
