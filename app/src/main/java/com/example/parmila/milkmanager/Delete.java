package com.example.parmila.milkmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.Activities.login;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;

public class Delete extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);

    Catalog c= new Catalog();
    String cEmail=c.email;

    EditText pass;
    Button del;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        pass=findViewById(R.id.pass);
        del=findViewById(R.id.delete_acc);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.checkCust(cEmail,pass.getText().toString().trim()))
                {
                    helper.deleteCust(cEmail);
                    Toast.makeText(Delete.this,"Your account  has been deleted succesfully!!",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Delete.this, login.class);
                    Delete.this.startActivity(i);
                }
                else
                {
                    Toast.makeText(Delete.this, "Enter valid password",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}
