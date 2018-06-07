package com.a_hamoud.listview_sqlserver;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    EditText ed_name, ed_age, ed_address, ed_email, ed_phone;
    DB db;
    Button btn_delete, btn_update;
    String timeString;
    AddCountryActivity addCountryActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ed_name = findViewById(R.id.ed1_name);
        ed_age = findViewById(R.id.ed1_age);
        ed_address = findViewById(R.id.ed1_address);
        ed_email = findViewById(R.id.ed1_email);
        ed_phone = findViewById(R.id.ed1_phone);

        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);

        addCountryActivity = new AddCountryActivity();
        timeString = addCountryActivity.getDateTime();

        db = (DB) getApplication();
        db.open();
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        //------------------------------------------------------------------
        Intent intent = getIntent();

        ed_name.setText(intent.getStringExtra("pl").trim());
        //------------------------------------------------------------------    
        view_();
        update_();
        delete_();
    }

    void delete_() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.save("delete from cairo where name_ = '" + ed_name.getText().toString().trim() + "'");
                finish();
            }
        });

    }


    void view_() {
        db.view_data2("select * from cairo where name_ = '" + ed_name.getText().toString().trim() + "'");

        ed_age.setText(db.h2.trim());
        ed_address.setText(db.h3.trim());
        ed_email.setText(db.h4.trim());
        ed_phone.setText(db.h5.trim());
    }

    void update_() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.save("update cairo set name_='" + ed_name.getText().toString().trim() + "',age_='" + ed_age.getText().toString().trim() + "',address_='" + ed_address.getText().toString().trim() + "',email_='" + ed_email.getText().toString().trim() + "',telephone_='" + ed_phone.getText().toString().trim() + "',date_time_='" + timeString + "' where name_ = '" + ed_name.getText().toString().trim() + "'");
                finish();
            }
        });
    }



}
