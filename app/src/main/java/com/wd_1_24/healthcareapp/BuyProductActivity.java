package com.wd_1_24.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyProductActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Product: RAM", "Capacity: 16GB", "", "", "20000.00"},
            {"Product: SSD Hard Disk", "Capacity: 1TB", "", "", "28000.00"},
            {"Product: CPU Cooler", "Type: Air Cooling", "", "", "5000.00"},
            {"Product: Graphics Card", "Memory: 8GB GDDR6", "", "", "50000.00"},
            {"Product: Motherboard", "Socket Type: AM4", "", "", "25000.00"},
            {"Product: Power Supply", "Wattage: 750W", "", "", "15000.00"},
            {"Product: Case Fan", "Size: 120mm", "", "", "2500.00"},
            {"Product: Mechanical Keyboard", "Size: 120mm", "", "", "12000.00"},
            {"Product: Gaming Mouse", "Switch Type: Cherry MX Blue", "", "", "7500.00"}

    };


    private String[] packageDetails = {
            "Capacity: 16GB\n"+"Speed: 3200Mhz\n"+"Brand: Kingstan",
            "Capacity: 1TB\n"+"Speed: 3000Mhz\n"+"Brand: WD",
            "Type: Air Cooling\n"+"Socket Compatibility: LGA1200\n"+"Brand: Cooler Master",
            "Memory: 8GB GDDR6\n"+"Interface: PCIe 4.0\n"+"Brand: NVIDIA",
            "Socket Type: AM4\n"+"Form Factor: ATX\n"+"Brand: ASUS",
            "Wattage: 750W\n"+"Efficiency: 80 Plus Gold\n"+"Brand: Corsair",
            "Size: 120mm\n"+"Type: RGB\n"+"Brand: NZXT",
            "Switch Type: Cherry MX Blue\n"+"Backlighting: RGB\n"+"Brand: Razer",
            "DPI: 16000\n"+"Buttons: 8\n"+"Brand: Logitech"
    };



    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;
    ListView lst;
    Button btnGotoCart,btnBack;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);


        lst = findViewById(R.id.LisiViewBuyProduct);
        btnGotoCart = findViewById(R.id.buttonGotoCart);
        btnBack = findViewById(R.id.buttonBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyProductActivity.this,HomeActivity.class));
            }
        });

        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(BuyProductActivity.this,CartActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);

        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[] { "line1", "line2", "line3", "line4", "line5" },
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(BuyProductActivity.this, BuyProductDetailActivity.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",packageDetails[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);
            }
        });

    }
}