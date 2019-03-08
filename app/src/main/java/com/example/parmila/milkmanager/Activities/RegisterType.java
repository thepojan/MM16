package com.example.parmila.milkmanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.parmila.milkmanager.R;

public class RegisterType extends AppCompatActivity {

    RadioGroup reg_type;
    RadioButton consumer;
    RadioButton seller;
    Button next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_type);
        reg_type=findViewById(R.id.reg_type_radiobtn);
        consumer=findViewById(R.id.consumer);
        seller=findViewById(R.id.seller);
        next=findViewById(R.id.reg_type_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId =reg_type.getCheckedRadioButtonId();
                if(selectedId==R.id.consumer)
                {
                    Intent consumerIntent = new Intent(RegisterType.this, CRegister.class);
                    RegisterType.this.startActivity(consumerIntent);
                }
                else if(selectedId==R.id.seller)
                {
                    Intent sellerIntent = new Intent(RegisterType.this, SRegister.class);
                    RegisterType.this.startActivity(sellerIntent);
                }
                else
                {
                    Toast.makeText(RegisterType.this, "please select an option", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
