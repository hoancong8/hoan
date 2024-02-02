package com.example.myapplication;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.BlankFragment.*;
import com.google.android.material.navigation.NavigationView;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MainActivity extends AppCompatActivity{
    String ip="192.168.1.235";
    String classs="net.sourceforge.jtds.jdbc.Driver";
    String db="test";
    String tk="sa";
    String pwd="sa";
String dalennha="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.tvsql);
        findViewById(R.id.btnSQL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata(textView);
            }
        });
    }

    public Connection CONN(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn=null;
        try{
            Class.forName(classs).newInstance();
            String ConnURL= "jdbc:jtds:sqlserver://192.168.1.235:1433;databasename=test;user=sa;password=sa;";
            conn= DriverManager.getConnection(ConnURL);
            Log.d("thong bao","kết nối thành công ---------------------------------");
        }catch (Exception e){
            Log.d("LOOIX__TO",e.getMessage());
        }
        return conn;
    }

    public void getdata(TextView textView){
        String name="";
        int lop=0;
        try {
            Connection connection =CONN();

            if(connection!=null){
                PreparedStatement preparedStatement=connection.prepareStatement("select * from tblTest");
                ResultSet resultSet=preparedStatement.executeQuery();
                while(resultSet.next()){ // Di chuyển con trỏ tới dòng dữ liệu đầu tiên
                    name = resultSet.getString(1);
                    lop = resultSet.getInt(2);
                    Log.i("du_lieu", name + "-----" + lop);
                    textView.append("họ tên : "+name+"\t"+"lớp : "+lop+"\n");
                }
                preparedStatement.close();
                Toast.makeText(MainActivity.this,"kết nối thành công!!",Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,name+"-----"+lop,Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.d("LOOIX__TO",e.getMessage());
        }
    }
    public void adddata(){
        //dalen nha kekeke
    }
}
