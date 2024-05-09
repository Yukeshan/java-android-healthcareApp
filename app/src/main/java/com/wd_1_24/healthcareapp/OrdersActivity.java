package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrdersActivity extends AppCompatActivity {

    String[][] orderDetails = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        btnBack = findViewById(R.id.buttonBack);
        lst = findViewById(R.id.LisiViewOrderDetails);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrdersActivity.this, HomeActivity.class));
            }
        });

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "").toString();
        ArrayList dbData = db.getOrderData(username); //orderDetails method must be implemented in database class

        orderDetails = new String[dbData.size()][];
        for (int i = 0; i < orderDetails.length; i++) {
            orderDetails[i] = new String[6];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            orderDetails[i][0] = strData[0];
            orderDetails[i][1] = strData[1];
            orderDetails[i][2] = strData[6];
            orderDetails[i][3] = strData[4];
        }

        list = new ArrayList();
        for (int i = 0; i < orderDetails.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", orderDetails[i][0]);
            item.put("line2", orderDetails[i][1]);
            item.put("line3", orderDetails[i][2]);
            item.put("line4", orderDetails[i][3]);
            item.put("line5", orderDetails[i][4]);
            list.add(item);

        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[] { "line1", "line2", "line3", "line4", "line5" },
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);




    }
}