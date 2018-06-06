package com.a_hamoud.listview_sqlserver;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    Button bt_add_new, btn_search;
    DB db;
    ListView list;
    EditText ed_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_add_new = (Button) findViewById(R.id.btn_add_freiend);
        btn_search = (Button) findViewById(R.id.btn_search);
        list = (ListView) findViewById(R.id.listView1);
        ed_search = findViewById(R.id.ed_search);
        db = (DB) getApplication();
        db.open();

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        bt_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(MainActivity.this, AddCountryActivity.class);
                startActivity(int1);
            }
        });
        //------------------------------------------------------------
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = (String) list.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("pl", s.trim());
                startActivity(intent);
            }
        });

        //------------------------------------------------------------

        view_data();
        view_data2();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view_data();
    }

    void view_data() {
        db.view_data(" select * from cairo");

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>
                (getApplicationContext(), android.R.layout.simple_list_item_1, db.arrayList);
        list.setAdapter(adapter);
    }

    void view_data2() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.view_data(" select * from cairo where name_ like '%"+ed_search.getText().toString().trim()+"%'");

                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>
                        (getApplicationContext(), android.R.layout.simple_list_item_1, db.arrayList);
                list.setAdapter(adapter);
            }
        });

    }
}
