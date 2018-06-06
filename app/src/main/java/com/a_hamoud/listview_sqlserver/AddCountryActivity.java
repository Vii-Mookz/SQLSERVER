package com.a_hamoud.listview_sqlserver;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;

public class AddCountryActivity extends AppCompatActivity {
    EditText ed_name,ed_mail,ed_address, ed_telephone,ed_age;
    Button btn_save;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);
        ed_name = (EditText) findViewById(R.id.ed_f_name);
        ed_address = (EditText) findViewById(R.id.ed_f_address);
        ed_mail = (EditText) findViewById(R.id.ed_f_email);
        ed_age = (EditText) findViewById(R.id.ed_f_age);
        ed_telephone = (EditText) findViewById(R.id.ed_f_telephone);
        btn_save = findViewById(R.id.btn_save);

            StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        db = (DB) getApplication();

        db.open();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.save("insert into cairo values ('"+ed_name.getText().toString().trim()+"','"+ed_age.getText().toString().trim()+"','"+ed_address.getText().toString().trim()+"','"+ed_mail.getText().toString().trim()+"','"+ed_telephone.getText().toString().trim()+"')");
                finish();
            }
        });
    }
}
