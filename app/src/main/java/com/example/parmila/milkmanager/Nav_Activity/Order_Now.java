package com.example.parmila.milkmanager.Nav_Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.modules.Preference;


public class Order_Now extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
    RadioGroup type;
    RadioButton Cow,Buffalo;
    EditText quantity;
    Button cal_cost, Submit;
    EditText Total_Cost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);
        initViews();
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

                saveToPreference();
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



    public void initViews()
    {
        type=findViewById(R.id.m_type);
        Cow=findViewById(R.id.cow);
        Buffalo=findViewById(R.id.buffalo);
        quantity=findViewById(R.id.m_qtty);
        cal_cost=findViewById(R.id.calc_cost);
        Submit=findViewById(R.id.submit);
        Total_Cost=findViewById(R.id.final_cost);
    }


    public void saveToPreference()
    {
        Preference p=new Preference();
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

        p.setP_Type(mType);

        p.setP_Quantity(Integer.parseInt(quantity.getText().toString()));

        Toast.makeText(this,"Succesfully saved your preference",Toast.LENGTH_SHORT).show();
    }
}
