package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;

    private Button btnCheckout, btnBack;
    private String[][] packages = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnCheckout=findViewById(R.id.buttonCheckOut);
        btnBack=findViewById(R.id.buttonBack);
        tvTotal=findViewById(R.id.textViewTotalCost);
        lst=findViewById(R.id.listViewCart);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Database db = new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount = 0;
        ArrayList dbData =db.getCartData(username);
        //Toast.makeText(getApplicationContext(),"Product already added",Toast.LENGTH_SHORT).show();

        packages = new String[dbData.size()][];
        for (int i=0;i<packages.length;i++)
        {
            packages[i] = new String[5];
        }

        for (int i=0;i<dbData.size();i++)
        {
        String arrData = dbData.get(i).toString();
        String[] strData = arrData.split(java.util.regex.Pattern.quote("$")); packages[i][0] = strData[0];
        packages[i][4] = "Cost: "+strData[1]+"/";
        totalAmount = totalAmount + Float.parseFloat(strData[1]);
        tvTotal.setText("Total Cost: "+totalAmount);
        }

        list = new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item );
        }


        sa = new SimpleAdapter( this, list,
                R.layout.multi_lines,
                new String[] { "line1", "line2", "line3", "line4", "line5" },
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,HomeActivity.class));
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent( CartActivity.this, BuyProductBookActivity.class);
                it.putExtra("price", tvTotal.getText());
                startActivity(it);
            }
        });


    }
}