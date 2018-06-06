package com.a_hamoud.listview_sqlserver;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DB extends Application{
    ResultSet resultSet;
    String ip = "192.168.102.155";
    String DBName = "TEST_JDBC";
    String DBUserNameStr = "sa";
    String DBPasswordStr = "12345";
    Statement st;
    Connection connection ;
    String url = "";

    public  void open()
    {
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            url = "jdbc:jtds:sqlserver://" + ip +";databaseName="+ DBName + ";user=" + DBUserNameStr+ ";password=" + DBPasswordStr + ";";
            connection = DriverManager.getConnection(url);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
    }


    //-------------------------------------------------------
    public void save(String sql) {
        try {
            st = connection.createStatement();
            st.executeQuery(sql);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    //----------------------------------------------------
    ArrayList<String> arrayList = new ArrayList<>();

    public void view_data(String sql) {
        try {

            st = connection.createStatement();
            resultSet = st.executeQuery(sql);
            arrayList.clear();

            while (resultSet.next()) {
                arrayList.add(resultSet.getString("name_"));

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    //----------------------------------------------------

//    public String h1 = "";
    public String h2 = "";
    public String h3 = "";
    public String h4 = "";
    public String h5 = "";

    public void view_data2(String sql) {
        try {

            st = connection.createStatement();
            resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
//                h1 = resultSet.getString("name_");
                h2 = resultSet.getString("age_");
                h3 = resultSet.getString("address_");
                h4 = resultSet.getString("email_");
                h5 = resultSet.getString("telephone_");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    //----------------------------------------------------
}
