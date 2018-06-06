package com.a_hamoud.listview_sqlserver;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alaeddin on 5/21/2017.
 */

public class ConnectionHelper extends Application{

//    String ip,db,DBUserNameStr,DBPasswordStr;
    String ip = "192.168.102.155";
    String db = "test_jdbc";
    String DBUserNameStr = "sa";
    String DBPasswordStr = "12345";
    Statement st;
    Connection connection ;
    String url = "";
    public  void open()
    {



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            url = "jdbc:jtds:sqlserver://" + ip +";databaseName="+ db + ";user=" + DBUserNameStr+ ";password=" + DBPasswordStr + ";";
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
}
